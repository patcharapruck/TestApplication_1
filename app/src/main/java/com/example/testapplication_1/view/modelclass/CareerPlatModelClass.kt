package com.example.testapplication_1.view.modelclass

class CareerPlatModelClass (val career_id: Int, val career_name: String? ,val career_salary: Int?) {

    private var careerID: Int = 0
    private var careerName:String? = ""
    private var careerSalary:Int? = 0

    init {
        initInstanes()
    }

    private fun initInstanes() {

        this.careerID = career_id
        this.careerName = career_name
        this.careerSalary = career_salary
    }

    fun getCareerID(): Int {
        return careerID
    }


    fun getCareerName(): String? {
        return careerName
    }

    fun getCareerSalary(): Int? {
        return careerSalary
    }
}