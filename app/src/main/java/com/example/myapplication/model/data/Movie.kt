package com.example.myapplication.model.data

import android.os.Parcelable
import com.example.myapplication.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val originalTitle: String? = "",
        val genres: String? = "",
        val releaseDate: Int? = 0,
        val overview: String? = "",
        val posterPath: Int = 0,
        val popularity: Number = 0,
        val runtime: Int = 0
) : Parcelable

fun getMovieList(): List<Movie> {
    return listOf(
            Movie(
                    "Lost in Translation",
                    "Drama",
                    2004,
                    "Билл Мюррей и Скарлетт Йоханссон в фильме Софии Копполы",
                    R.drawable.poster_lost_in_translation,
                    7,
                    95
            ),
            Movie(
                    "Manhattan",
                    "drama",
                    1974,
                    "Желая бросить опостылевшую работу и привычный круг знакомств, телевизионный сценарист заводит роман с любовницей своего друга.\n",
                    R.drawable.manhattan,
                    7,
                    120
            ),
            Movie(
                    "Криминальное Чтиво",
                    "crime",
                    1994,
                    " Двое бандитов Винсент Вега и Джулс Винфилд ведут философские беседы в перерывах между разборками и решением проблем с должниками криминального\n" +
                            "босса Марселласа Уоллеса.",
                    R.drawable.pulp_fiction,
                    8,
                    110
            ),
            Movie(
                    "Сломанные цветы",
                    "drama",
                    2005,
                    "Утро для престарелого «Дон Жуана» Дона Джонстона начинается с того, разбирая почту, Дон обнаруживает розовый конверт. В письме неизвестная сообщает, что двадцать лет назад, расставшись с Доном, обнаружила, что беременна. Показав письмо другу Уинстону - любителю детективов - Дон получил от того совет: вспомнить всех своих подружек,\n" +
                            "которые у него были 20 лет назад и посетить их, выяснив, какая из них родила ему сына.",
                    R.drawable.broken_flowers,
                    7,
                    95
            ),
            Movie(
                    "Твин Пикс",
                    "thriller",
                    1990,
                    "История начинается с известия о находке обнаженного тела старшеклассницы Лоры Палмер, завёрнутого в полиэтилен и выброшенного волнами на берег озера.\n" +
                            "Постепенно зритель открывает для себя темную и страшную сторону жизни обитателей на первый взгляд тихого и мирного городка.",
                    R.drawable.twin_peaks,
                    7,
                    0
            ),
            Movie(
                    "Французский диспетчер",
                    "comedy",
                    2021,
                    "Альманах о работе американской редакции во французской газете",
                    R.drawable.french_poster,
                    0,
                    120
            )
    )
}

