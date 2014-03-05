package com.makingdevs

class SurveyController {

  def index() { }

  def create() {
    def survey = new Survey(params)
    if(survey.save(flush:true)){
      redirect action:"showSurvey", id:survey.id
    }else{
      render view:"index"
    }
  }

  def showSurvey(){
    def survey = Survey.get(params.id)
    def questions = Question.list()
    [survey:survey,
     questions:questions]
  }

  def agregarPreguntas(){
    def survey = Survey.get(params.survey)
    def question= Question.getAll(params.id)
    survey.questions+=question
    redirect(action: "showSurvey", id:[survey.id])
  }

  def list(){
    def survey = Survey.list(sort: "title", order: "asc")
    [survey:survey]
  }
  
}