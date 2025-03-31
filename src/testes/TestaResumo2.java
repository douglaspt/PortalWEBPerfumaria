package testes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import org.hibernate.Session;

import dao.CriticaHibernate;
import dao.DescontoHibernate;
import dao.HibernateUtil;
import dao.LoteHibernate;
import entidades.Desconto;
import entidades.Lote;
import entidades.Usuario;

public class TestaResumo2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		String erro = "";
		String resultado = "";
    	Calendar data = new GregorianCalendar();
    	data.setLenient(false);
    	Calendar dataSistema = new GregorianCalendar();
    	ArrayList<File> files = new ArrayList<File>();

		new CriticaHibernate(session).limpa();
        new DescontoHibernate(session).limpa();
		
		//new CriticaHibernate(session).criticar();
        
      //  System.out.println("ok");
        
        //-29/03/2012
        
	//	List<Desconto> d = new DescontoHibernate(session).buscarTodos();//CriticaHibernate(session).buscaResumoCritica();
	//	System.out.println(d.size());
	
	//	for (Desconto desconto : d) {
	//		System.out.println(desconto.getIdEmpresa()+" - "+desconto.getMatricula()+" - "+desconto.getNome());
	//	}	
    	files.clear();
		
		File file = new File("C:/fechamento_mn_ate311203_v2.csv");
		try {
		Scanner arq = new Scanner(file);
		while (arq.hasNextLine()) {
		String linha = arq.nextLine();
		//String campos2[] = linha.split(";");
		System.out.println(linha);
		//System.out.println(campos2[0]+" - "+campos2[1]);
		}
		arq.close();
		} catch (IOException ioe) {
		ioe.printStackTrace();
		}

		FileInputStream stream = new FileInputStream(file);  
        InputStreamReader streamReader = new InputStreamReader(stream);  
        BufferedReader reader = new BufferedReader(streamReader);     
        
      //  FileWriter fileWriter = new FileWriter(destino);  
      //  PrintWriter writer = new PrintWriter(fileWriter);
        
        Usuario usuario = new Usuario();
        usuario.setId(1l);
        
        Lote l = new Lote();
        l.setId(dataSistema.getTimeInMillis());
        l.setDataEnvio(dataSistema.getTime());
        l.setIpOrigem("192.168.0.0");
        //l.setUsuarioEnvio(usuario);
   //     l.setNomeArquivo(item.getFileName());
        l.setNomeArquivo(file.getName());
        LoteHibernate lH = new LoteHibernate(session);
        lH.salva(l);
        
     //   limparDescontos();
        DescontoHibernate dH = new DescontoHibernate(session);
               
        String line = null;
        int linha = 0;
        
               
        while(((line=reader.readLine())!=null)&&(erro.equals(""))) {  
            String campos[] = line.split(";");
            Desconto d = new Desconto();
            dH = new DescontoHibernate(session);
            d.setLinha(++linha);
            d.setLote(l);
            data.set(dataSistema.get(GregorianCalendar.YEAR) , dataSistema.get(GregorianCalendar.MONTH), 1);
            d.setReferencia(data.getTime());
            try {
            	System.out.println(linha + " -> 0-"+ campos[0]+"  1-"+campos[1]+"  2-"+campos[2]+"  3-"+campos[3]+"  4-"+campos[4]+"  5-"+campos[5]+"  6-"+campos[6]+"  7-"+campos[7]+"  7-"+campos[8]);
            } 
            catch (java.lang.ArrayIndexOutOfBoundsException e) {
            	erro = "  Linha: "+linha + " - Número de Campos Inferior ao especificado no Lay-Out";
				System.out.println(erro);
			}
            
            
          	try{
           		if (!campos[0].equals(""))
           		  d.setIdBeneficiario(Integer.parseInt(campos[0].replace(".", "")));
           		else	
           			System.out.println("  Linha: "+linha + " -Cod. Beneficiario em branco");
           		             		
           	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Cod_beneficiario  no formato 000000)";
               	System.out.println(erro);
			}

           	try{
           		d.setIdEmpresa(Integer.parseInt(campos[1]));
           		d.setMatricula(Integer.parseInt(campos[2].replace(".", "")));
           	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Empresa / Matrícula no formato 00 e 000000)";
               	System.out.println(erro);
			}	
           	d.setNome(campos[3]);
           	
          	try{
          		d.setNumeroNF(Integer.parseInt(campos[4].replace(".", "")));
        	} catch (java.lang.NumberFormatException e) {
               	erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Numero de Nota Fiscal no formato 00000)";
               	System.out.println(erro);
			}
    			//System.out.println(campos[5].substring(6, 10)+campos[5].substring(3, 5)+campos[5].substring(0, 2));
            try { 
            	data.set(Integer.parseInt(campos[5].substring(6, 10)) , Integer.parseInt(campos[5].substring(3, 5)) -1, Integer.parseInt(campos[5].substring(0, 2)));
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
				d.setVlInformado(Double.parseDouble(campos[6].replace(",", ".")));
				//System.out.println(String.valueOf(Float.parseFloat(campos[5].replace(",", ".")))); 
			} catch (java.lang.StringIndexOutOfBoundsException e) {
				erro = "  Linha: "+linha + " - Formatação de Campo Numérico fora do especificado no Lay-Out ( Somente Números para Valor no formato 0000000000 )";
			} 	     		

			//d.setVlPago(Double.parseDouble(s));
			//d.setDocumento(campos[14]);
       // 	d.setFormaPagamento(Integer.parseInt(campos[7]));
            d.setNomeComprador(campos[8]);
            
            dH.salva(d);
              
        }
        if (!erro.isEmpty()){
        	resultado = " Somente " + --linha + " registros foram lidos com sucesso.";
        	System.out.println(resultado);
        } else { 
        	resultado = linha + " registros lidos com sucesso.";
        	System.out.println(resultado);
        }	
       	files.add(file);
      //  writer.close();  
    //    fileWriter.close();  
        reader.close();  
        streamReader.close();  
        stream.close();  
		
		
		
	}
	
	
	
	

	
	

}
