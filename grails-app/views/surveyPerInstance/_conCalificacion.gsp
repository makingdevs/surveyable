<div class="row-fluid">
  <div class="span6">
    <strong>${question.description}</strong>
    <g:each in="${question.answers.sort()}" var="respuesta" status="a">   <label class="radio">
        ${respuesta.description}
        <input type="radio" name="question[${index}].description" value="${respuesta.id}" required/>
        </label>
    </g:each>
  </div>
  <div class="span6 form-inline">
    <strong>Calificacion</strong><br>
    <g:each in="${question.satisfaccion}" var="rango" status="a"> 
      <label class="radio">
        ${rango}
      <input type="radio" name="question[${index}].rango" value="${rango}" required/>  
      </label>
    </g:each>
  </div>
  <div class="span12">
    <label><strong>Comentarios:</strong></label>
    <g:textArea rows="3" class="span12" name="question[${index}].comentario" style="overflow:auto; span12; resize:none"></g:textArea>
    <g:hiddenField name="question[${index}].id" value="${question.id}" />
  </div>
</div>
