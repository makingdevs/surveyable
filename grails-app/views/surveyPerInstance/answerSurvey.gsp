<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Answer Questionary</title>
</head>
<body>
  <div class="container">
    <h1>${surveyPerInstance.survey.title}</h1>
    <h1>${surveyPerInstance.survey.codeName}</h1>
    <h4>${surveyPerInstance.survey.description} - Responde a las siguientes preguntas</h4>
    <hr>
    <div class="row-fluid">
      <g:form name="surveyForm" id="surveyForm" url="[action:'guardarSurvey',controller:'SurveyPerInstance']">

        <g:each in="${surveyPerInstance.survey.questions.sort()}" var="question" status="i">
          <g:if test="${question.questionType==QuestionType.CON_CALIFICACION}">
            <g:render template="conCalificacion" model="[question:question,index:i]"/>
          </g:if>
          <g:elseif test="${question.questionType==QuestionType.SIN_CALIFICACION}">
            <g:render template="sinCalificacion" model="[question:question,index:i]"/>
          </g:elseif>
          <g:elseif test="${question.questionType==QuestionType.ABIERTA}">
            <g:render template="open" model="[question:question,index:i]"/>
          </g:elseif>
          <hr>
        </g:each>

        <g:hiddenField name="surveyPerInstance" value="${surveyPerInstance.id}" />

        <div class="offset3 span6 offset3">
          <input class="btn btn-large btn-block btn-primary" type="submit" value="Enviar" />
        </div>

      </g:form>
    </div>
  </div>
</body>
</html>