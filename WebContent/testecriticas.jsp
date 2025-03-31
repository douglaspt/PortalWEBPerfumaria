<%@page contentType="text/html"%>
<%@page pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://primefaces.prime.com.tr/ui" prefix="p" %> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional// EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="/perfumaria/resources/jquery.maskedinput-1.2.1.js"></script>
          
    </head>

    <body>
    
    
    <f:view >
    

<rich:panel  >


	<f:facet name="header">
		<h2 align="center"><h:outputText value="#{gerenciadorCriticas.titulo}" styleClass="title" /></h2>
	</f:facet>
	<aj4:form id="menuForm" ajaxSubmit="true" >
		<aj4:commandLink value="Home" action="#{gerenciadorDescontos.goHome}" /><br/><br/>
	</aj4:form>	
	<aj4:form id="pesquisaForm" ajaxSubmit="true" >
		<h:message for="mesAno" style="color: darkred" />
        <h:panelGrid columns="4" cellspacing="4" cellpadding="4">
	      <h:outputText value="Mês/Ano : "  />
		  <h:inputText id="mesAno" value="#{gerenciadorCriticas.mesAno}" size="20" required="true" requiredMessage="É Obrigatório o preenchimento do Mês / Ano de Referência no Formato MM/AAAA Ex. 09/2009">
		    <rich:jQuery selector="#mesAno" query="mask('99/9999')" timing="onload"/>	
		  </h:inputText>
		  <h:outputText value="Ajuda?" id="ajuda"  />
			<rich:toolTip id="textoAjuda" followMouse="true" direction="top-right" showDelay="500" for="ajuda" style="background-color:white" >
			<rich:effect event="onmouseout" type="Opacity" params="duration:0.8,from:1.0,to:0.3" />
			<p><strong style="color: red">Campos Obrigatórios para Pesquisa.</strong></p>
			<strong style="color: darkred">Mês de Referência.</strong><br/>
			 <span  style="white-space:nowrap">Para realizar a pesquisa é obrigatório o preenchimento do <strong>Mês de Referência.</strong></span><br/>
			 O Mês de referencia trata-se do mês e ano em que o desconto foi enviado a Folha de Pagamento, ou seja, o mês que o desconto deve ser pago.<br/>
			 Esta informação deve ser digitada no formato MM/AAAA. Ex. 05/2009 (Para pesquisar os descontos da folha mês de maio de 2009)<br/>
			<br/>
			<p style="color: red"><strong>Campos Opcionais para Pesquisa.</strong></p>
			<strong style="color: darkred">Linha</strong><br/>
			Número da linha onde esta informação está no arquivo que foi carregado.<br/>
			Útil caso queira pesquisar um registro que está na linha X do arquivo excel.<br/><br/>
			<strong style="color: darkred">ID Beneficiário</strong><br/>
			Código interno do Beneficiario Cadastrado no IMASF<br/>
			Pode-se pesquisar digitando parte do número<br/><br/>
			<strong style="color: darkred">Empresa e Matrícula</strong><br/>
			Número da Empresa e Matrícula do Funcionário na Prefeitura/Autarquia/Empresa que tem convênio odontológico<br/>
			Principais empresas: 5 - Prefeitura, 16 - IMASF, 15 - Faculdade<br/><br/>
			<strong style="color: darkred">Data da Compra</strong><br/>
			Data da Compra<br/>
			Preencha uma data<br/><br/>
			<strong style="color: darkred">Crítica</strong><br/>
			Selecione um tipo de Crítica clicando no Campo.<br/>
			As Críticas são informações de inconsistências que o sistema gerou após a carga do arquivo de Descontos.<br/><br/>			
      		<strong style="color: darkred">Nome</strong><br/>
			Preencha o nome ou parte do nome ou sobrenome para localizar os descontos.<br/>			
      		</rich:toolTip>		  
		  <h:outputText value="Linha: "  />
		  <h:inputText id="linha" value="#{gerenciadorCriticas.linha}" size="20"></h:inputText>
		  <h:outputText value="ID Beneficiário: "  />
		  <h:inputText id="idBeneficiario" value="#{gerenciadorCriticas.idBeneficiario}" size="20"></h:inputText>
		  <h:outputText value="Empresa  :  "  />
		  <h:inputText id="empresa" value="#{gerenciadorCriticas.idEmpresa}" size="20"></h:inputText>
		  <h:outputText value="Matricula: "  />
		  <h:inputText id="matricula" value="#{gerenciadorCriticas.matricula}" size="20"></h:inputText>
		  
		  <h:outputText value="Numero NF  :  "  />
		  <h:inputText id="numeroNF" value="#{gerenciadorCriticas.numeroNF}" size="20"></h:inputText>
		  <h:outputText value="Série : "  />
		  <h:inputText id="serie" value="#{gerenciadorCriticas.serie}" size="20"></h:inputText>
		  <h:outputText value="ID Controle : "  />
		  <h:inputText id="idControle" value="#{gerenciadorCriticas.idControle}" size="20"></h:inputText>
		  <h:outputText value="Data da Compra :  "  />     	
		  <rich:calendar id="dataCompra" datePattern="dd/MM/yyyy" value="#{gerenciadorCriticas.dataCompra}"> 
			<rich:ajaxValidator event="onblur"/>
		  </rich:calendar>
		  <h:outputText value="Critica: "  />
          <h:selectOneMenu id="classificacao" value="#{gerenciadorCriticas.classificacaoCritica.id}" >
		  	<f:selectItems  value="#{gerenciadorCriticas.classificacoes}" />
    	  </h:selectOneMenu>
    	  <rich:spacer width="60%" height="20px"/>
    	  <br/>
    	  <h:outputText value="Pagamento: "  />
          <h:selectOneMenu id="formaPagamento" value="#{gerenciadorCriticas.formaPagamento.id}" >
		  	<f:selectItems  value="#{gerenciadorCriticas.pagamentos}" />
    	  </h:selectOneMenu>
    	  <rich:spacer width="60%" height="20px"/>
    	  <br/>
    	  <h:outputText value="Nome "  />
		  <h:inputText id="nome" value="#{gerenciadorCriticas.nome}" size="50"></h:inputText>
		  <br/>

	    </h:panelGrid>
	    <aj4:commandButton id="btnP" value="Pesquisar" action="#{gerenciadorCriticas.pesquisaPorParametro}" reRender="pesquisaForm, listaForm" />
	</aj4:form>
	<br/>
	

<aj4:form id="listaForm" ajaxSubmit="true"  reRender="criticas">
<rich:panel id="listagem" rendered="#{not empty gerenciadorCriticas.criticas}">	

	<rich:dataTable id="criticas" border="1" value="#{gerenciadorCriticas.criticas}" var="c" binding="#{gerenciadorCriticas.objDataTable}" rendered="#{not empty gerenciadorCriticas.criticas}" rows="10">
		<rich:column sortBy="#{c.desconto.linha}">
			<f:facet name="header"><h:outputText value="Linha"/></f:facet>
			<h:outputText value="#{c.desconto.linha}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.referencia}">
			<f:facet name="header"><h:outputText value="Data"/></f:facet>
			<h:outputText value="#{c.desconto.referencia}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.desconto.idEmpresa}">
			<f:facet name="header"><h:outputText value="Emp."/></f:facet>
			<h:outputText value="#{c.desconto.idEmpresa}" />
		</rich:column>		
		<rich:column sortBy="#{c.desconto.matricula}">
			<f:facet name="header"><h:outputText value="Matricula"/></f:facet>
			<h:outputText value="#{c.desconto.matricula}" />
		</rich:column>	
		<rich:column sortBy="#{c.desconto.nome}">
			<f:facet name="header"><h:outputText value="Nome"/></f:facet>
			<h:outputText value="#{c.desconto.nome}" rendered="#{c.classificacaoCritica.id < 900}" style="color: red" />
			<h:outputText value="#{c.desconto.nome}" rendered="#{c.classificacaoCritica.id == 901}" style="color: darkred" />
			<h:outputText value="#{c.desconto.nome}" rendered="#{c.classificacaoCritica.id == 900 || (c.classificacaoCritica.id > 901 && c.classificacaoCritica.id < 1000)}" style="color: blue" />
			<h:outputText value="#{c.desconto.nome}" rendered="#{c.classificacaoCritica.id == 1000}"  style="color: darkblue" />
			<h:outputText value="#{c.desconto.nome}" rendered="#{c.classificacaoCritica.id > 1000}"  style="color: darkgreen" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.numeroNF}">
			<f:facet name="header"><h:outputText value="Nº NF"/></f:facet>
			<h:outputText value="#{c.desconto.numeroNF}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.dataCompra}">
			<f:facet name="header"><h:outputText value="Dt. Compra"/></f:facet>
			<h:outputText value="#{c.desconto.dataCompra}" ><f:convertDateTime pattern = "dd/MM/yyyy"/></h:outputText>
		</rich:column>
		<rich:column sortBy="#{c.desconto.formaPagamento.descricao}">
			<f:facet name="header"><h:outputText value="Pgto."/></f:facet>
			<h:outputText value="#{c.desconto.formaPagamento.descricao}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.vlInformado}">
			<f:facet name="header"><h:outputText value="Vl. Inform."/></f:facet>
			<h:outputText value="#{c.desconto.vlInformado}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>		
		<rich:column sortBy="#{c.desconto.vlPago}">
			<f:facet name="header"><h:outputText value="Vl. Pago"/></f:facet>
			<h:outputText value="#{c.desconto.vlPago}" ><f:convertNumber pattern="###,###,##0.00"/></h:outputText>
		</rich:column>	
		<rich:column sortBy="#{c.classificacaoCritica.descricao}">
			<f:facet name="header"><h:outputText value="Critica"/></f:facet>
			<h:outputText value="#{c.classificacaoCritica.descricao}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.parcela}">
			<f:facet name="header"><h:outputText value="Parc."/></f:facet>
			<h:outputText value="#{c.desconto.parcela}" />
		</rich:column>
		<rich:column sortBy="#{c.desconto.nParcela}">
			<f:facet name="header"><h:outputText value="Nº Parc."/></f:facet>
			<h:outputText value="#{c.desconto.nParcela}" />
		</rich:column>
		<rich:column sortBy="#{c.obsCritica}">
			<f:facet name="header"><h:outputText value="Observações"/></f:facet>
			<h:outputText value="#{c.obsCritica}" />
		</rich:column>	
		
		<rich:column>
			<f:facet name="header"><h:outputText value="Funções"/></f:facet>
			<aj4:commandLink id="filtro" value="Filtrar" action="#{gerenciadorCriticas.filtrarPorMatriculaSelecionada}" reRender="listaForm" rendered="#{gerenciadorCriticas.extratoMatricula==''}"/>			
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="filtro">
            	<span  style="white-space:nowrap">Clique para <strong>Filtrar os Dados desta Matrícula</strong>. Serão mostrados todos os descontos desta Matrícula de todos os meses</span>
      		</rich:toolTip>
			<aj4:commandLink id="extrato" value="Extrato" action="#{gerenciadorCriticas.gerarExtrato}" reRender="listaForm"  rendered="#{gerenciadorCriticas.extratoMatricula!=''}"/>
			<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="extrato">
            	<span  style="white-space:nowrap">Clique para <strong>Emissão do Extrato</strong> desta Matrícula.</span>
      		</rich:toolTip>
		</rich:column>
		<f:facet name="footer">
			<rich:datascroller for="criticas"></rich:datascroller>
		</f:facet>		
	</rich:dataTable>
	<br/>
	

    <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkExcel">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong> Excel</strong>.	</span>
    </rich:toolTip>
            <rich:toolTip followMouse="true" direction="top-right" showDelay="500" for="linkCVS">
       	<span style="white-space:nowrap">Exportar dados para um arquivo <strong>CVS</strong> (Arquivo TXT).	</span>
    </rich:toolTip>
        
	 <h:commandLink  id="linkExcel"  rendered="#{not empty gerenciadorCriticas.criticas}">  
	 	 <p:graphicImage value="images/excel.jpg"  style="border:none"/>  
    	 <p:dataExporter type="xls" target="criticas" fileName="criticas"/> 
	 </h:commandLink>	
</rich:panel>	 
</aj4:form>		

</rich:panel>             
        
<rich:jQuery id="enterTab" query="keydown(function(e){if (e.keyCode == 13){jQuery(e).next().focus();return false;}})"/>        
        
    </f:view>
    </body>
</html>
