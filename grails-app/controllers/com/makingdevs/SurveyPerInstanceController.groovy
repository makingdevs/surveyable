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
    def surveyPerInstance=surveyPerInstanceService.saveAnswersInSurvey(params)
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

