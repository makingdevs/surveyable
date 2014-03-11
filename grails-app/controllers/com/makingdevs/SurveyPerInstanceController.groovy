package com.makingdevs

import com.makingdevs.*

class SurveyPerInstanceController {

  def surveyPerInstanceService
  def surveyPerInstanceLinkService

  def answerSurvey(){
    def surveyPerInstance=SurveyPerInstance.get(params.id)
    [surveyPerInstance:surveyPerInstance,
    numPreguntas:surveyPerInstance.survey.questions.size(),
    url:params.url]
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
    surveyPerInstance.surveyPerInstanceStatus=SurveyPerInstanceStatus.CONTESTADO
    redirect(action: "showSurveyPerInstance", params: [surveyPerInstanceId: surveyPerInstance.id,url:params.url])
  }

  def showSurveyPerInstance(){
    def surveyPerInstance = SurveyPerInstance.get(params.surveyPerInstanceId)
    [surveyPerInstance:surveyPerInstance,
    url:params.url]
  }

  def agregar(){
    def clazzIntance = Class.forName(params.clazz).newInstance()
    def instance = clazzIntance.get(params.instanceId)
    def surveyPerInstanceLink=surveyPerInstanceLinkService.createSurveyPerInstance(instance,params.surveyId.toLong())
    def url=params.url-grailsApplication.metadata.'app.name'-'/'
    redirect(url:url)
  }
    
}

