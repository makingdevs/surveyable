package com.makingdevs

import com.makingdevs.*

class SurveyPerInstanceController {

  def surveyPerInstanceService

  def answerSurvey(){
    def survey=Survey.get(params.id)
    def surveyPerInstance=surveyPerInstanceService.instanceSurvey(survey.id)
    [surveyPerInstance:surveyPerInstance]
  }

  def guardarSurvey(){

  }

}
