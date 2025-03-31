package testes;

import java.util.HashMap;
import java.util.List;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanArrayDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.hibernate.Session;

import dao.FormaPagamentoHibernate;
import dao.HibernateUtil;
import entidades.FormaPagamento;

public class TestaJasperReport {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String fileName = "C:/workspace/perfumaria/src/testes/reportDescontosPerfumaria.jasper";
		String outFileName = "C:/workspace/perfumaria/src/testes/reportDescontosPerfumaria.pdf";
		HashMap hm = new HashMap();
		//hm.put("", "");
		Session session = HibernateUtil.getSession("hibernate.cfg.xml");
		//List criticas = new CriticaHistoricoHibernate(session).buscarPorParametros(null, null,  "16", "514", null, null, null, null, null, null, null, null);
		//System.out.println(criticas.size());
		List pagamento = new FormaPagamentoHibernate(session).buscarTodos();
		System.out.println(pagamento.size());
		session.close();
		
		JRBeanArrayDataSource jrdts = new JRBeanArrayDataSource(pagamento.toArray());  
		 System.out.println("Array");  
		try {
	           // Fill the report using an empty data source
            JasperPrint print = JasperFillManager.fillReport(fileName, hm, jrdts);
            System.out.println("Fill the report using an empty data source");         
            // Create a PDF exporter
            JRExporter exporter = new JRPdfExporter();
            System.out.println("Create a PDF exporter");  
            
            // Configure the exporter (set output file name and print object)
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, outFileName);
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            System.out.println(" Configure the exporter");  
            
            // Export the PDF file
            exporter.exportReport();

        } catch (JRException e) {
            e.printStackTrace();
            System.out.println(" Erro1");  
            System.exit(1);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(" Erro2");  
            System.exit(1);
        }


	}

}
