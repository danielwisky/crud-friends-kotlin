package br.com.danielwisky.friends.utils

import br.com.danielwisky.friends.UnitTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.data.mongodb.core.query.Criteria

class CriteriaUtilsTest : UnitTest() {

    @Test
    fun `should add equal when value is not empty`() {
        val criteria = mutableListOf<Criteria>()
        CriteriaUtils.addEqualsIfNotEmpty(criteria, "name", "João da Silva")
        assertFalse(criteria.isEmpty())
    }

    @Test
    fun `should not add equal when value is empty`() {
        val criteria = mutableListOf<Criteria>()
        CriteriaUtils.addEqualsIfNotEmpty(criteria, "name", "")
        assertTrue(criteria.isEmpty())
    }

    @Test
    fun `should not add equal when value is null`() {
        val criteria = mutableListOf<Criteria>()
        CriteriaUtils.addEqualsIfNotEmpty(criteria, "name", null)
        assertTrue(criteria.isEmpty())
    }
}
