package ru.sectorsj.where_to_go.repository.locationRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sectorsj.where_to_go.dto.Location

private val locations = listOf(
    Location(
        1,
        "Стадион",
        "Крутейший стадион",
        "Крутейший стадион",
        "ул. Стадион",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        2,
        "Музей",
        "Крутейший музей",
        "Крутейший музей",
        "ул. Музей",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        3,
        "Концертный зал",
        "Крутейший концертный зал",
        "Крутейший концертный зал",
        "ул. Музей",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        4,
        "Кинотеатр",
        "Крутейший кинотеатр",
        "Крутейший кинотеатр",
        "ул. Кинотеатр",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        5,
        "Ресторан",
        "Крутейший ресторан",
        "Крутейший ресторан",
        "ул. Ресторан",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        6,
        "Ночной клуб",
        "Крутейший ночной клуб",
        "Крутейший ночной клуб",
        "ул. Музей",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        7,
        "Пивной паб",
        "Крутейший пивной паб",
        "Крутейший пивной паб",
        "ул. Пивной паб",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    ),
    Location(
        8,
        "Пивной паб",
        "Крутейший пивной паб",
        "Крутейший пивной паб",
        "ул. Пивной паб",
        "08:00",
        "18:00",
        "12:00",
        "13:00",
        "www.image.com",
        "www.site.com",
        12.00,
        25.00
    )
)

class LocationRepositoryImpl() : LocationRepository {
    override val data: LiveData<List<Location>> = MutableLiveData(locations)
}