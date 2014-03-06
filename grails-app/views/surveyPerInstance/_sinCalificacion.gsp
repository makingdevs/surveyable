<strong>${question.description}</strong>
<g:each in="${question.answers.sort()}" var="respuesta" status="a"> 
  <label class="radio">
    ${respuesta.description}
    <input type="radio" name="question[${index}].description" value="${respuesta.id}" required/>
  </label>
</g:each>
<label><strong>Comentarios:</strong></label>
<g:textArea rows="3" class="span12" name="question[${index}].comentario" style="overflow:auto; span12; resize:none"></g:textArea>
<g:hiddenField name="question[${index}].id" value="${question.id}" />