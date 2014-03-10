package com.makingdevs

import com.makingdevs.*
import grails.transaction.Transactional

class SurveyPerInstanceLinkService {

  def surveyPerInstanceService

  def createSurveyPerInstance(def instance, Long surveyId) {
    if(!Surveyable.class.isAssignableFrom(instance.class)){
      throw new Exception("Pelaz!")
    }
    def surveyPerInstanceLink =new SurveyPerInstanceLink(
      surveyPerInstance:surveyPerInstanceService.instanceSurvey(surveyId),
      type:instance.class.getSimpleName(),
      surveyPerInstanceRef:instance.id)
      .save(flush:true)
    surveyPerInstanceLink
  }

  def findSurveyPerInstanceByCodeName(Long idInstance, String codeName){
    def surveyPerInstanceLinkEncontrado=SurveyPerInstanceLink.findAllBySurveyPerInstanceRef(idInstance)
    def surveyPerInstanceLinkReturn
    surveyPerInstanceLinkEncontrado.each{it -> 
      if(it.surveyPerInstance.survey.codeName.equalsIgnoreCase(codeName)){
        surveyPerInstanceLinkReturn=it
      }
    }
  surveyPerInstanceLinkReturn
  }

}
