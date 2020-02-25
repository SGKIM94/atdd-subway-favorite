package atdd.path.dao;

import atdd.path.SoftAssertionTest;
import atdd.path.domain.Favorite;
import atdd.path.domain.Station;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

import static atdd.path.TestConstant.TEST_STATION;
import static atdd.path.fixture.FavoriteFixture.STATION_NAME;
import static atdd.path.fixture.FavoriteFixture.getDaoFavorites;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class FavoriteDaoTest extends SoftAssertionTest {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    private FavoriteDao favoriteDao;
    private StationDao stationDao;

    @BeforeEach
    void setUp() {
        favoriteDao = new FavoriteDao(jdbcTemplate);
        favoriteDao.setDataSource(dataSource);
        stationDao = new StationDao(jdbcTemplate);
        stationDao.setDataSource(dataSource);
    }

    @DisplayName("Favorite 에 Station 을 저장이 성공하는지")
    @Test
    public void save() {
        //given
        Station station = stationDao.save(TEST_STATION);

        //when
        Favorite favorite = favoriteDao.save(new Favorite(station));

        //then
        assertThat(favorite.getId()).isNotNull();
        assertThat(favorite.getStation().getName()).isEqualTo(STATION_NAME);
    }

    @DisplayName("Id 로 Favorite 을 조회할 수 있는지")
    @Test
    public void findById() {
        //given
        Station station = stationDao.save(TEST_STATION);
        Favorite savedFavorite = favoriteDao.save(new Favorite(station));

        //when
        Favorite favorite = favoriteDao.findById(savedFavorite.getId());

        //then
        assertThat(favorite.getId()).isNotNull();
        assertThat(favorite.getStation()).isEqualTo(savedFavorite.getStation());
    }

    @DisplayName("findById 로 나온 결과를 Favorite 로 만들어주는지")
    @Test
    public void mapFavorite(SoftAssertions softly) {
        //when
        Favorite favorite = favoriteDao.mapFavorite(getDaoFavorites());

        //then
        softly.assertThat(favorite.getId()).isNotNull();
        softly.assertThat(favorite.getStation().getName()).isEqualTo(STATION_NAME);
    }
}