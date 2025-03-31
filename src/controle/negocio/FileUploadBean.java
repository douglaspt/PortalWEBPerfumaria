package controle.negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import dao.CriticaHibernate;
import dao.DescontoHibernate;
import dao.HibernateUtil;
import dao.LoteHibernate;
import entidades.Desconto;
import entidades.FormaPagamento;
import entidades.Lote;
import entidades.Usuario;

public class FileUploadBean {
	
	private ArrayList<UploadItem> files = new ArrayList<UploadItem>();
	private int uploadsAvailable = 5;
	private boolean autoUpload = false;
	private boolean useFlash = false;
	private String erro = "";
	private String resultado = ""; 
	private String nomeComprador = "";

	private Session session = HibernateUtil.getSession("hibernate.cfg.xml");
	
	private GerenciadorEMail mail = new GerenciadorEMail();
	
	private void limparDescontos(){
		new CriticaHibernate(session).limpa();
        new DescontoHibernate(session).limpa();
	}

	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	public FileUploadBean() {
	}

	public void listener(UploadEvent event) throws Exception{
		erro = "";
		resultado = "";
    	Calendar data = new GregorianCalendar();
    	data.setLenient(false);
    	Calendar dataSistema = new GregorianCalendar();
    	SimpleDateFormat sdfddMMyyyy_HHmmss = new SimpleDateFormat("ddMMyyyy_HHmm");
		
		ServletContext serveletContext=(ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    	//String destino = serveletContext.getRealPath("../ROOT/imasf_docs/odontologico/afiliados_"+sdfddMMyyyy_HHmmss.format(new Date())+".csv");
		String destino = serveletContext.getRealPath("teste.csv");

    	//Date data = new Date();
    	files.clear();
    	
        UploadItem item = event.getUploadItem();
        File file = item.getFile();
        
        FileInputStream stream = new FileInputStream(file);  
        InputStreamReader streamReader = new InputStreamReader(stream);  
        BufferedReader reader = new BufferedReader(streamReader);     
        
        FileWriter fileWriter = new FileWriter(destino);  
        PrintWriter writer = new PrintWriter(fileWriter);
        
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        
        Lote l = new Lote();
        l.setId(dataSistema.getTimeInMillis());
        l.setDataEnvio(dataSistema.getTime());
        l.setIpOrigem("192.168.0.0");
        //l.setUsuarioEnvio(usuario);
        l.setNomeArquivo(item.getFileName());
        LoteHibernate lH = new LoteHibernate(session);
        lH.salva(l);
        
        limparDescontos();
        DescontoHibernate dH = new DescontoHibernate(session);
               
        String line = null;
        int linha = 0;
        
        while(((line=reader.readLine())!=null)&&(erro.equals(""))) {  
            String campos[] = line.split(";");
            Desconto d = new Desconto();
            dH = new DescontoHibernate(session);
            d.setLinha(++linha);
            d.setLote(l);
            data.set(
            dataSistema.get(GregorianCalendar.YEAR) , dataSistema.get(GregorianCalendar.MONTH), 1);
            d.setReferencia(data.getTime());
            try {
            	System.out.println(linha + " -> "+ campos[0]+"  1-"+campos[1]+"  2-"+campos[2]+"  3-"+campos[3]+
            	"  4-"+campos[4]+"  5-"+campos[5]+"  6-"+campos[6]+"  7-"+campos[7]+"  8-"+campos[8]+"  9-"+campos[9]+
            	"  10-"+campos[10]+" 11-"+campos[11]+" 12-"+campos[12]+" 13-"+campos[13]+" 14-"+campos[14]+" 15-"+campos[15]);
            } 
            catch (java.lang.ArrayIndexOutOfBoundsException e) {
            	erro = "  Linha: "+linha + " - Número de Campos Inferior ao especificado no Lay-Out";
				System.out.println(erro);
			}
            
           	try{
          		d.setIdInterno(Integer.parseInt(campos[0].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - IdInterno - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 00000)";
               	System.out.println(erro);
			}
            
          	try{
           		if (!campos[1].equals(""))
           		  d.setIdBeneficiario(Integer.parseInt(campos[1].replace(".", "")));
           		else	
           			System.out.println("  Linha: "+linha + " -Cod. Beneficiario em branco");
           		             		
           	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Cod. Beneficiario - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Cod_beneficiario  no formato 000000)";
               	System.out.println(erro);
			}

           	try{
           		d.setIdEmpresa(Integer.parseInt(campos[2]));
           		d.setMatricula(Integer.parseInt(campos[3].replace(".", "")));
           	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - IdEmp/Matricula - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Empresa / Matrícula no formato 00 e 000000)";
               	System.out.println(erro);
			}		

           	try {
           		d.setNome(campos[4]);
			} catch (Exception e) {
				erro = "  Linha: "+linha + " - Nome do Titular com formato errado ou não esta Preenchido";// TODO: handle exception
				System.out.println(erro);
			}
           	 	
          	try{
          		d.setNumeroNF(Integer.parseInt(campos[5].replace(".", "")));
          		d.setSerie(Integer.parseInt(campos[6].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - NumeroNF/Serie - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 00000)";
               	System.out.println(erro);
			}
           	
        	try { 
            	data.set(Integer.parseInt(campos[7].substring(6, 10)) , Integer.parseInt(campos[7].substring(3, 5)) -1, Integer.parseInt(campos[7].substring(0, 2)));
            	d.setDataCompra(data.getTime());
            	  	
			} catch (java.lang.NumberFormatException e) {
				erro = "  Linha: "+linha + " - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DD.MM.YYYY - Ex. 01022009 A)";
				System.out.println(erro);
			} catch (java.lang.IllegalArgumentException e) {
				erro = "  Linha: "+linha + " - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DD.MM.YYYY - Ex. 01022009 B)";
				System.out.println(erro);
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				erro = "  Linha: "+linha + " - Formatação de Campo Data (Referencia/Nascimento/Adesão) fora do especificado no Lay-Out ( DD.MM.YYYY - Ex. 01022009 C)";
				System.out.println(erro);
			}

			try{
				d.setVlInformado(Double.parseDouble(campos[8].replace(",", ".")));
				//System.out.println(String.valueOf(Float.parseFloat(campos[5].replace(",", ".")))); 
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				erro = "  Linha: "+linha + " - VlInformado - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Valor no formato 0000000000 )";
				System.out.println(erro);
			} 
			FormaPagamento formaPagamento = new FormaPagamento();
			try {
				formaPagamento.setId(Integer.parseInt(campos[9]));
			} catch (Exception e) {
				erro = "  Linha: "+linha + " - Forma de Pagamento não existe";// TODO: handle exception
				System.out.println(erro);
			} 
			
			try {
	      		d.setFormaPagamento(formaPagamento);
			} catch (Exception e) {
				erro = "  Linha: "+linha + " - Forma de Pagamento com formato errado ou não esta Preenchido";// TODO: handle exception
				System.out.println(erro);
			}
        	//d.setFormaPagamento(formaPagamento);
			if (campos[10].length() > 254) {
				//erro = "  Linha: "+linha + " - Nome do Comprador não pode ser maior do que 255 caracteres - Porém foi carregado";// TODO: handle exception
				System.out.println(erro);
				nomeComprador = campos[10].substring(0, 254);
				System.out.println(nomeComprador);
			}
			else {
				nomeComprador = campos[10];	
			}
			
        	try {
        		d.setNomeComprador(nomeComprador);
			} catch (Exception e) {
				erro = "  Linha: "+linha + " - Nome do Comprador com formato errado ou não esta Preenchido";// TODO: handle exception
				System.out.println(erro);
			}
	    	try {
	    		d.setDocumento(campos[11]);
			} catch (Exception e) {
				erro = "  Linha: "+linha + " - Documento com formato errado ou não esta Preenchido";// TODO: handle exception
				System.out.println(erro);
			}
        	
           	try{
          		d.setIdControle(Integer.parseInt(campos[12].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - IdControle - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 00000)";
               	System.out.println(erro);
			}
           	try{
          		d.setParcela(Integer.parseInt(campos[13].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Parcela - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 00000)";
               	System.out.println(erro);
			}
           	try{
          		d.setnParcela(Integer.parseInt(campos[14].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Nº de Parcela - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 00000)";
               	System.out.println(erro);
			}
        	try{
        		d.setIdSolicitacao(Integer.parseInt(campos[15].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - ID Solicitacao - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 0000000)";
               	System.out.println(erro);
			}
        	
       		dH.salva(d);
              
        }
        if (!erro.isEmpty()){
        	resultado = " Somente " + --linha + " registros foram lidos com sucesso.";
        	System.out.println(resultado);
        }else { 
        	resultado = linha + " registros lidos com sucesso.";
        	System.out.println(resultado);
        }	

       	files.add(item);
        writer.close();  
        fileWriter.close();  
        reader.close();  
        streamReader.close();  
        stream.close();  
        
        //mail.sendEmail("Sistema Odontológico - Carga de Arquivos", "Foi carregado o banco de dados de descontos odontológicos com "+linha+" registros.");
        
        uploadsAvailable--;
        
    }
	
	public String doUpload(){
		System.out.println("Fazendo Upload");
		return null;
	}
	
	public String doStop(){
		System.out.println("Stop Upload");
		return null;
	}
	
	public String doCancel(){
		System.out.println("Upload Cancelado");
		return null;
	}
	
	
	public String doClear(){
		System.out.println("Upload Limpo");
		return null;
	}
	
	public String clearUploadData() {
		erro = "";
		resultado = "";
        DescontoHibernate dH = new DescontoHibernate(session);
        dH.limpa();
		files.clear();
		setUploadsAvailable(5);
		return null;
	}

	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	public ArrayList<UploadItem> getFiles() {
		return files;
	}

	public void setFiles(ArrayList<UploadItem> files) {
		this.files = files;
	}

	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	public boolean isAutoUpload() {
		return autoUpload;
	}

	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	public boolean isUseFlash() {
		return useFlash;
	}

	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash;
	}

	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

	
	
}
