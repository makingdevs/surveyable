<label><strong>${question.description}</strong></label>
<textarea rows="3" class="span12" name="question[${index}].description" style="overflow:auto; resize:none" required></textarea>
<g:hiddenField name="question[${index}].id" value="${question.id}" />