package atdd.path.application;

import atdd.path.application.dto.favorite.FavoriteCreateRequestView;
import atdd.path.dao.FavoriteDao;
import atdd.path.domain.Favorite;
import org.springframework.stereotype.Service;

@Service
public class FavoriteService {
    private FavoriteDao favoriteDao;

    public FavoriteService(FavoriteDao favoriteDao) {
        this.favoriteDao = favoriteDao;
    }

    public Favorite save(FavoriteCreateRequestView favorite) {
        return favoriteDao.save(favorite.toEntity());
    }
}