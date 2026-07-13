package app.watchlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatchlistRepository extends JpaRepository<WatchlistItem, UUID>
{

}
