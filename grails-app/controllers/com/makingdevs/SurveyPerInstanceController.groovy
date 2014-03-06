package com.makingdevs

import com.makingdevs.*

class SurveyPerInstanceController {

  def surveyPerInstanceService

  def answerSurvey(){
    def survey=Survey.get(params.id)
    def surveyPerInstance=surveyPerInstanceService.instanceSurvey(survey.id)
    [surveyPerInstance:surveyPerInstance,
    numPreguntas:surveyPerInstance.answerPerInstances.size()]
  }

  def guardarSurvey(){
    def idPregunta = []
    def idPreguntas= []
    def respuestas=[]
    def satisfaccion=[]
    def observaciones=[]
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      idPregunta << params.getAt("question[${i}]").id
    }
    def preguntas=Question.getAll(idPregunta)
    def surveyPerInstance = SurveyPerInstance.get(params.num)
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      switch(preguntas.getAt(i).questionType) {
        case QuestionType.CON_CALIFICACION:
          idPreguntas << params.getAt("question[${i}]").id
          respuestas << params.getAt("question[${i}]").description
          satisfaccion << params.getAt("question[${i}]").rango
          observaciones << params.getAt("question[${i}]").comentario
        break
        case QuestionType.SIN_CALIFICACION:
          idPreguntas << params.getAt("question[${i}]").id
          respuestas << params.getAt("question[${i}]").description
          satisfaccion << null
          observaciones << params.getAt("question[${i}]").comentario
        break
        case QuestionType.ABIERTA:
          idPreguntas << params.getAt("question[${i}]").id
          respuestas << params.getAt("question[${i}]").description
          satisfaccion << null
          observaciones << null
        break
      }
    }
    for (int i = 0; i < params.numPreguntas.toLong(); i++) {
      surveyPerInstanceService.addAnswer(idPregunta.getAt(i),respuestas.getAt(i),satisfaccion.getAt(i),observaciones.getAt(i),surveyPerInstance.id)
    }
    redirect(action: "showSurveyPerInstance", params: [surveyPerInstanceId: surveyPerInstance.id])
  }

  def showSurveyPerInstance(){
    def surveyPerInstance = SurveyPerInstance.get(params.surveyPerInstanceId)
    [surveyPerInstance:surveyPerInstance]
  }
    
}

