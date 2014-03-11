<%@ page import="com.makingdevs.Surveyable" %>
<%@ page import="com.makingdevs.SurveyPerInstanceStatus" %>
<ul>
  <g:each in="${listSurveyAvailable}" var="survey">
    <li>
      ${survey.codeName}
      <g:each in="${surveyLinks}" var="a">
        <g:if test="${a?.surveyPerInstance?.survey?.id==survey.id}">
          <g:set var="surveyLink" value="${a}" />
        </g:if>
      </g:each>
      <g:if test="${surveyLink?.surveyPerInstance?.survey?.id==survey.id}">
        <g:if test="${surveyLink?.surveyPerInstance?.surveyPerInstanceStatus==SurveyPerInstanceStatus.CONTESTADO}">
          <g:link class="btn-small btn-primary" controller="surveyPerInstance" action="showSurveyPerInstance" params="[surveyPerInstanceId:surveyLink?.surveyPerInstance?.id,url:request.forwardURI]">
          <i class='icon-search icon-white'></i> Ver
          </g:link>
        </g:if>
        <g:else>
          <g:link class="btn-small btn-primary" controller="surveyPerInstance" action="answerSurvey" params="[id:surveyLink.surveyPerInstance.id,url:request.forwardURI]">
          <i class='icon-pencil icon-white'></i> Contestar
          </g:link>
        </g:else>
      </g:if>
      <g:else>
        <g:link class="btn-small btn-primary" controller="surveyPerInstance" 
        action="agregar" params="[surveyId:survey.id, instanceId : instance.id, clazz : instance.class.name,url:request.forwardURI]">      
        <i class='icon-plus-sign icon-white'></i> Agregar
        </g:link>
      </g:else>
      <g:if test="${surveyLink?.surveyPerInstance?.surveyPerInstanceStatus!=null}">
        <g:if test="${surveyLink?.surveyPerInstance?.surveyPerInstanceStatus==SurveyPerInstanceStatus.CONTESTADO}">
          <span class="label label-success">${surveyLink?.surveyPerInstance?.surveyPerInstanceStatus}</span>
        </g:if>
        <g:else>
          <span class="label label-info">${surveyLink?.surveyPerInstance?.surveyPerInstanceStatus}</span>
        </g:else>
      </g:if>
      <g:else>
        <span class="label">SIN_AGREGAR</span>
      </g:else>
    </li>
    <g:set var="surveyLink" value="${null}" />
  </g:each>
</ul>