Cистема принятия голосов граждан, и определение победителя

Система должна давать возможность пользователям регистрироваться, совершать вход.
После входа можно посмотреть информацию о кандидатах и проголосовать за одного из них.
Все данные о пользователях и кандидатах храняться в базе данных. 
Также система позволяет админу запускать новые выборы, загружая кандидатов из файлов.

Инструкция по запуску:

Проект создавался в среде Apache NetBeans IDE 20.
Достаточно открыть его в этой среде и нажать F6 (Run project).
Либо можно на Shift+F11 собрать проект в папке dist. Желательно положить в неё папку textFiles с файлами кандидатов.
В системе изначально присутствует один админ:
login: admin
password: admin
Можно наделить полномочиями админа другого пользователя, указав его логин, либо же отобрать полномочия.
Пароль должен состоять из латинских букв, цифр и подчеркиваний и быть не короче 5 символов. Логин - тоже

Файлы кандидатов должны иметь расширение .txt и иметь следующий формат:
ФИО кандидата
Год рождения (в виде числа)
Город проживания
Партия
Доп. информация

Кандидаты с выборов 2024 уже лежат в папке textFiles, так что можно указывать путь на неё.

Пример:
Владимир Владимирович Путин
1952
Москва
Самовыдвижение
Президент Российской Федерации

При запуске новых выборов информация о предыдущих будет выгружаться в папку Save, в файлы с названиями Elections*номер выборов*

Использованные темы:

1. Обработка ошибок - используется постоянно.
2. Библиотека классов Java - используются даты для определения начала и конца выборов,
Постоянно используется обработка строк, использованы регулярные выражения для проверки пароля/логина
3. Работа с файлами - кандидаты подгружаются из файлов,
система выгружает в файл данные о прошедших выборах.
4. Многопоточное программирование - приложение создаёт дополнительный поток, 
отслеживающий конец выборов и вызывающий окно результатов.
5. Функциональные интерфейсы - используются предикаты для фильтрации кандидатов.
6. Коллекции - кандидаты часто хранятся в коллекциях типа HashSet, HashMap или ArrayList.
7. Лямбда-выражения - часто используются для фильтрации кандидатов.
8. Stream API - используются вместе с лямбда-выражениями для фильтрации кандидатов.
9. Работа с базами данных - база данных используется для хранения данных.
