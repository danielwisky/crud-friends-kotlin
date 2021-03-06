package br.com.danielwisky.friends.gateways.outputs.mongodb.repositories

import br.com.danielwisky.friends.domains.FriendFilter
import br.com.danielwisky.friends.gateways.outputs.mongodb.documents.FriendDocument
import br.com.danielwisky.friends.utils.CriteriaUtils
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.stereotype.Component

@Component
class FriendDocumentCustomRepositoryImpl(
    private var mongoTemplate: MongoTemplate,
) : FriendDocumentCustomRepository {

    override fun search(filter: FriendFilter, pageable: Pageable): Page<FriendDocument> {
        val criteria = buildCriteria(filter)
        val friends = mongoTemplate.find(query(criteria).with(pageable), FriendDocument::class.java)
        val total = mongoTemplate.count(query(criteria), FriendDocument::class.java)
        return PageImpl(friends, pageable, total)
    }

    private fun buildCriteria(filter: FriendFilter): Criteria {
        val criteria = mutableListOf(Criteria.where("id").ne(null))
        CriteriaUtils.addEqualsIfNotEmpty(criteria, "name", filter.name)
        CriteriaUtils.addEqualsIfNotEmpty(criteria, "cellphone", filter.cellphone)
        CriteriaUtils.addEqualsIfNotEmpty(criteria, "email", filter.email)
        return Criteria().andOperator(*criteria.toTypedArray())
    }
}
