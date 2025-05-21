package mk.ukim.finki.emt.repository;

import mk.ukim.finki.emt.model.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsernameAndPassword(String username, String password);

    Optional<User> findByUsername(String username);

    // Does not change the Lazy FetchType in the User class for carts
    @EntityGraph(attributePaths = {})
    @Query("SELECT u FROM User u")
    List<User> findAllWithoutCarts();

    // Change the Lazy FetchType in the User class for carts
    // 1 query for user joining carts.
    @EntityGraph(
            type = EntityGraph.EntityGraphType.FETCH,
            attributePaths = {"carts"}
    )
    @Query("select u from User u")
    List<User> fetchAll();

    // Does not change the Lazy FetchType in the User class for carts
//    1 query to fetch all users
//    1 query per user to fetch their carts 😬
//    (That’s N+1 queries — slow when you have many users)
    @EntityGraph(
            type = EntityGraph.EntityGraphType.LOAD,
            attributePaths = {"carts"}
    )
    @Query("select u from User u")
    List<User> loadAll();

    @Query("SELECT u FROM User u WHERE u NOT IN (SELECT h.user FROM HostProfile h)")
    List<User> findNonHostUsers();
}
