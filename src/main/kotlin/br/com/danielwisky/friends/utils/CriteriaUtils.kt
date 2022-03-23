package br.com.danielwisky.friends.utils

import org.springframework.data.mongodb.core.query.Criteria

class CriteriaUtils {

    companion object {
        fun addEqualsIfNotEmpty(criterias: MutableList<Criteria>, key: String, value: String?) {
            if (!value.isNullOrEmpty()) {
                criterias.add(Criteria.where(key).`is`(value))
            }
        }
    }
}