<%@ page import="com.makingdevs.QuestionType"%>
<!DOCTYPE html>
<html>
<head>
  <meta name="layout" content="bootstrap"/>
  <title>Create question</title>
  <g:javascript library="jquery" plugin="jquery"/>
</head>
<body>
  <div class="container">
    <h1>Captura pregunta</h1>
    <div class="row-fluid">
      <g:form name="questionForm" action="save">
        <div class="span6">
          <label>Description</label>
          <textarea class="field span12" rows="4" name="description"></textarea>
        </div>
        <div class="span6" id="satisfaccion">
          <label>QuestionType</label>
          <g:select name="questionType" from="${QuestionType.values()}"/>
          
        </div>
        <div class="span12">
          <input class="btn btn-primary" type="submit" value="Enviar" />
        </div>
      </g:form>
    </div>
  </div>
  <script>
  $( "#questionType" ).change(function() {
    if($("#questionType option:selected").text()=="CON_CALIFICACION"){
      $( "<br><input type='text' name='satisfaccion'>" ).appendTo( '#satisfaccion' );
    }
  });
  </script>
</body>
</html>
