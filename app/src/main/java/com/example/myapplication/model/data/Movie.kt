package com.example.myapplication.model.data

import android.os.Parcelable
import com.example.myapplication.R
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
        val id: Int? = 0,
        val original_title: String? = "",
        val title: String? = "",
        val release_date: String? = "",
        val overview: String? = "",
        val poster_path: String? = "",
        val vote_average: Double? = 0.0,
        val runtime: Int? = 0
) : Parcelable

fun getMovieList() = listOf(
        Movie(
                153,
                "Lost in Translation",
                "Трудности Перевода",
                "2004",
                "Билл Мюррей и Скарлетт Йоханссон в фильме Софии Копполы",
                R.drawable.poster_lost_in_translation.toString(),
                7.5,
                95
        ),
        Movie(
                696,
                "Manhattan",
                "Манхэттен",
                "1974",
                "Желая бросить опостылевшую работу и привычный круг знакомств, телевизионный сценарист заводит роман с любовницей своего друга.\n",
                R.drawable.manhattan.toString(),
                7.5,
                120
        ),
        Movie(
                680,
                "Pulp Fiction",
                "Криминальное Чтиво",
                "1994",
                " Двое бандитов Винсент Вега и Джулс Винфилд ведут философские беседы в перерывах между разборками и решением проблем с должниками криминального\n" +
                        "босса Марселласа Уоллеса.",
                R.drawable.pulp_fiction.toString(),
                8.4,
                110
        ),
        Movie(
                308,
                "Broken Flowers",
                "Сломанные цветы",
                "2005",
                "Утро для престарелого «Дон Жуана» Дона Джонстона начинается с того, разбирая почту, Дон обнаруживает розовый конверт. В письме неизвестная сообщает, что двадцать лет назад, расставшись с Доном, обнаружила, что беременна. Показав письмо другу Уинстону - любителю детективов - Дон получил от того совет: вспомнить всех своих подружек,\n" +
                        "которые у него были 20 лет назад и посетить их, выяснив, какая из них родила ему сына.",
                R.drawable.broken_flowers.toString(),
                7.3,
                95
        ),
        Movie(
                1923,
                "Twin Peaks",
                "Твин Пикс",
                "1990",
                "История начинается с известия о находке обнаженного тела старшеклассницы Лоры Палмер, завёрнутого в полиэтилен и выброшенного волнами на берег озера.\n" +
                        "Постепенно зритель открывает для себя темную и страшную сторону жизни обитателей на первый взгляд тихого и мирного городка.",
                R.drawable.twin_peaks.toString(),
                7.4,
                0
        ),
        Movie(
                542178,
                "French Dispatch",
                "Французский диспетчер",
                "2021",
                "Альманах о работе американской редакции во французской газете",
                R.drawable.french_poster.toString(),
                0.0,
                120
        )
)


