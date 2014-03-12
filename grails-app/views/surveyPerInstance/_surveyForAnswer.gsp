<%@ page import="com.makingdevs.Surveyable" %>
<%@ page import="com.makingdevs.SurveyPerInstanceStatus" %>
<ul>
  <g:if test="${surveyLinks.size()==0}">
    No tienes encuestas
  </g:if>
  <g:else>
    <g:each in="${surveyLinks}" var="surveyLink">
      <li>
        ${surveyLink?.surveyPerInstance?.survey?.codeName}
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
      </li>
    </g:each>
  </g:else>
</ul>