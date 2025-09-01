package com.example.apipractice.fake

import com.example.apipractice.data.network.dto.AmphibianDto
import com.example.apipractice.domain.model.Amphibian

object FakeDataSource {

    val amphibianDtoList = listOf(

        AmphibianDto(
            name = "name1",
            description = "description1",
            imgUrl = "img1"
        ),
        AmphibianDto(
            name = "name2",
            description = "description2",
            imgUrl = "img2"
        )
    )

    val amphibianList = listOf(

        Amphibian(
            name = "name1",
            description = "description1",
            imgUrl = "img1"
        ),
        Amphibian(
            name = "name2",
            description = "description2",
            imgUrl = "img2"
        )
    )
}