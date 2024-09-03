package com.example.data.mappers

import com.example.data.db.entities.DefinitionEntity
import com.example.data.db.entities.LicenseEntity
import com.example.data.db.entities.MeaningEntity
import com.example.data.db.entities.PhoneticEntity
import com.example.data.db.entities.WordEntity
import com.example.domain.entities.Definition
import com.example.domain.entities.License
import com.example.domain.entities.Meaning
import com.example.domain.entities.Phonetic
import com.example.domain.entities.Word
import retrofit2.Retrofit

fun WordEntity.toDomainModel(): Word {
    return Word(
        word = word,
        phonetics = phonetics.map { it.toDomainModel() },
        meanings = meanings.map { it.toDomainModel() },
        license = license?.toDomainModel(),
        sourceUrls = sourceUrls
    )
}


fun PhoneticEntity.toDomainModel(): Phonetic {
    return Phonetic(
        text = text,
        audio = audio,
        sourceUrl = sourceUrl,
        license = license?.toDomainModel()
    )
}

fun MeaningEntity.toDomainModel(): Meaning {
    return Meaning(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toDomainModel() },
        synonyms = synonyms,
        antonyms = antonyms
    )
}

fun DefinitionEntity.toDomainModel(): Definition {
    return Definition(
        definition = definition,
        synonyms = synonyms,
        antonyms = antonyms,
        example = example
    )
}

fun LicenseEntity.toDomainModel(): License {
    return License(
        name = name,
        url = url
    )
}

fun Word.toEntityModel(): WordEntity {
    return WordEntity(
        word = word,
        phonetics = phonetics.map { it.toEntityModel() },
        meanings = meanings.map { it.toEntityModel() },
        license = license?.toEntityModel(),
        sourceUrls = sourceUrls
    )
}

fun Phonetic.toEntityModel(): PhoneticEntity {
    return PhoneticEntity(
        text = text,
        audio = audio,
        sourceUrl = sourceUrl,
        license = license?.toEntityModel()
    )
}

fun Meaning.toEntityModel(): MeaningEntity {
    return MeaningEntity(
        partOfSpeech = partOfSpeech,
        definitions = definitions.map { it.toEntityModel() },
        synonyms = synonyms,
        antonyms = antonyms
    )
}

fun Definition.toEntityModel(): DefinitionEntity {
    return DefinitionEntity(
        definition = definition,
        synonyms = synonyms,
        antonyms = antonyms,
        example = example
    )
}

fun License.toEntityModel(): LicenseEntity {
    return LicenseEntity(
        name = name,
        url = url
    )
}