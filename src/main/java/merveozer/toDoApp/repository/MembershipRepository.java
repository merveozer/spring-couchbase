package merveozer.toDoApp.repository;

import merveozer.toDoApp.model.MembershipEntity;
import org.springframework.data.couchbase.repository.CouchbaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends CouchbaseRepository<MembershipEntity, String> {
    MembershipEntity findByUserName(String userName);
}
