package com.luisgb.triland

data class ProfessionalModel(
    val email: String,
    val name: String,
    val surname: String,
    val phone: String,
    val description: String,
    val image: String
) {
    constructor():this(
        "",
        "",
        "",
        "",
        "",
        ""
    )
}