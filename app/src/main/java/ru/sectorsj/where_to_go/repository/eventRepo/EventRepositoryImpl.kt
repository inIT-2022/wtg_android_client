package ru.sectorsj.where_to_go.repository.eventRepo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.sectorsj.where_to_go.dto.Event

private val events = listOf(
    Event(1,
        "Открытие скейт-парка",
        "Крутейший скейт-парк",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(2,
        "Выставка в музее",
        "Крутейшая выставка",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(4,
        "Открытие аквапарка",
        "Крутейший аквапарк",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(5,
        "Открытие стадиона",
        "Крутейший стадион",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(6,
        "Концерт",
        "Крутейший концерт",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(7,
        "Событие",
        "Крутейшее событие",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(8,
        "Прьмьера фильма",
        "Крутейший фильм",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    ),
    Event(9,
        "Приезд В.В",
        "Крутейший приезд В.В",
        "12.07.2022",
        "13.07.22",
        "www.event.ru",
        200,
        200,
        true,
        22
    )

)

class EventRepositoryImpl() : EventRepository {
    override val data: LiveData<List<Event>> = MutableLiveData(events)
}