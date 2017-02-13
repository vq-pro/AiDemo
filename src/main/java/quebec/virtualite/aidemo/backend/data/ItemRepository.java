package quebec.virtualite.aidemo.backend.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Patrick on 2017-01-31.
 */
@Repository
public  interface ItemRepository extends CrudRepository<Item, Long>
{
    List<Item> findAllByOrderByTextAsc();
}
