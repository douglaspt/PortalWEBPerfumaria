<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="aj4"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Bem Vindo ao Sistema IMASF-Perfumaria - Menu Principal</title>

    <style>
        .optionList {
          height:22px;
        }
        .vertical-menu-cell {
            padding:0px 4px 0px 4px;
        }
    
    </style>

</head>
<body > 
    <SCRIPT LANGUAGE="JavaScript">    {
    	window.history.forward(1);
	}</SCRIPT> 
<script language="JavaScript">
window.onload = maxWindow;

function maxWindow()
{
window.moveTo(0,0);


if (document.all)
{
  top.window.resizeTo(screen.availWidth,screen.availHeight);
}

else if (document.layers||document.getElementById)
{
  if (top.window.outerHeight<screen.availHeight||top.window.outerWidth<screen.availWidth)
  {
    top.window.outerHeight = screen.availHeight;
    top.window.outerWidth = screen.availWidth;
  }
}
}

</script>	
<f:view>


	<rich:panel>
	<h:outputText value="Sistema de Processamento de Descontos de Perfumaria " style="FONT-WEIGHT: bold;font-size: 22px;color:#198A8A" />
	<f:facet name="header">
		<h2 align="center"><h:outputText value="IMASF - Sistema de Processamento de Descontos de Perfumaria" styleClass="title" /></h2>
	</f:facet>
		
<h:panelGrid columns="3"  cellpadding="6" cellspacing="2">
<!-- 
	<h:panelGroup >
		<h:graphicImage value="images/dentista1.jpg" style="border: none"/>
	</h:panelGroup>
-->
	<h:panelGroup>
	<aj4:form id="menuPrincipalForm">
	<h:outputText value="Bem Vindo, Sr(a): "  />
	<h:outputLabel value="#{perfumariaBean.usuarioLogado.nome}"/>
	<br/>
	<h:outputText>IP do Cliente :</h:outputText>
	<h:outputLabel value="#{perfumariaBean.ipCliente}" />
	<br/>
	<h:outputText>Seu ultimo Acesso foi em :</h:outputText>
	<h:outputLabel value="#{perfumariaBean.ultimoLogin}" ><f:convertDateTime pattern = "dd/MM/yyyy HH:mm"/></h:outputLabel>
	<br/><br/><br/><br/><br/>
	<h:outputText value="Selecione uma das op��es abaixo :"/>
	<br/><br/><br/>
 <!-- <aj4:commandLink id="descontos" value="Visualizar Arquivo Enviado" action="#{odontologicoBean.goDesconto}" /> -->
	
	        <h:panelGrid styleClass="vertical-menu-cell" columnClasses="optionList" columns="1" cellspacing="4" cellpadding="4">
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Usu�rios" direction="bottom-right" jointPoint="tr" disabled="#{perfumariaBean.usuarioLogado.colaboradorPrestador}">
                <rich:menuItem value="Cadastro de Usuarios" action="usuario" />
                <rich:menuItem value="Alterar Senha" action="mudancaSenha" />
            </rich:dropDownMenu>
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Cadastros" direction="bottom-right" jointPoint="tr" disabled="true">
                <rich:menuItem value="Consulta de Benefici�rios" disabled="true" />
            </rich:dropDownMenu>
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Processamento" direction="bottom-right"  jointPoint="tr" >
                <rich:menuItem value="Enviar Arquivo" action="#{perfumariaBean.envioArquivo}" >
                <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" >
            		<span  style="white-space:nowrap">Clique para o envio do <strong>Arquivo em Excel</strong> para carga dos descontos no Sistema.</span>
        		</rich:toolTip>
        		</rich:menuItem>
                <rich:menuItem value="Criticar Arquivo"  action="#{gerenciadorResumo.abrirProcessamento}">
				<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
 			    	<span  style="white-space:nowrap">Clique para o criticar o arquivo enviado.</span>
        		</rich:toolTip>                
                </rich:menuItem>
                <rich:menuSeparator/>
                <rich:menuItem value="Exportar Arquivo"  action="#{gerenciadorExportacao.abrir}"/>                            
            </rich:dropDownMenu>
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Folha" direction="bottom-right"  jointPoint="tr" disabled="true">           
                <rich:menuItem value="Enviar para Folha" >
				<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
            		<span  style="white-space:nowrap">Clique para enviar os descontos para Folha de Pagamento.</span>
        		</rich:toolTip>                
                </rich:menuItem>
                <rich:menuItem value="Processar Baixas"  >
					<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" >
            			<span  style="white-space:nowrap">Clique para Baixar os pagamentos da Folha.</span>
        			</rich:toolTip>                
                </rich:menuItem>               
            </rich:dropDownMenu>            
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Descontos" direction="bottom-right"  jointPoint="tr">
                <rich:menuItem value="Acessar Descontos / Criticas / Pagamentos" action="#{gerenciadorCriticas.abrir}" >
                <rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" >
            		<span  style="white-space:nowrap">Clique acessar os <strong>Descontos</strong>, e suas informa��es e status.</span>
        		</rich:toolTip>
        		</rich:menuItem>
            </rich:dropDownMenu>
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Relat�rios" direction="bottom-right"  jointPoint="tr">
                <rich:menuItem value="Extrato de Cr�ticas/Pagamentos" action="extratoCriticas"  >
                	<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" for="extrato1">
            			<span  style="white-space:nowrap">Clique emiss�o do <strong>Extrato de Descontos</strong> por Matr�cula.</span>
			        </rich:toolTip>	    
                </rich:menuItem>
                <rich:menuSeparator id="menuSeparator11" />
                <rich:menuItem value="Resumo de Cr�ticas" action="#{gerenciadorResumo.abrir}"  >
					<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip">
            			<span  style="white-space:nowrap">Clique emiss�o do <strong>Resumo das Cr�ticas dos Descontos</strong>.</span>
        			</rich:toolTip>                
                </rich:menuItem>
                <rich:menuItem value="Resumo Financeiro" action="#{gerenciadorResumoFinanceiro.abrir}" >
					<rich:toolTip followMouse="true" direction="top-right" showDelay="500" styleClass="tooltip" >
            			<span  style="white-space:nowrap">Clique emiss�o do <strong>Resumo Financeiro do Processamento de Descontos</strong>, por Status e Cr�tica.</span>
        			</rich:toolTip>	   
                </rich:menuItem>
            </rich:dropDownMenu>
            <rich:dropDownMenu style="border:1px solid #{a4jSkin.panelBorderColor}" value="Sair" direction="bottom-right"  jointPoint="tr">
                <rich:menuItem value="Sair do Sistema" action="#{perfumariaBean.doLogout}" />
            </rich:dropDownMenu>                     
        </h:panelGrid>
</aj4:form>		
</h:panelGroup>

<jsp:include page="acoesDoDia.jsp"/>

</h:panelGrid>


	</rich:panel>


<aj4:form >
	<p align="right"><h:outputText value="Desenvolvido por " style="border:none;font-size: 14px;color:#198A8A" /><a href="http://www.horizontesistemas.com" target="_blank"><h:graphicImage value="images/pch.jpg" style="align:right;border:none;font-size: 10px;color:#198A8A"/></a></p>
</aj4:form>

</f:view>
</body>
</html>