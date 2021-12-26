# cloudphoto-bot
В репозитории 2 папки: queue-trigger и tg-webhook

Они содержат код для облачных функций на java.
Папки с кодом нужно загружать в облачные функции в виде zip архива, в котором лежат папка src и pom.xml 

Функция queue-trigger должна быть установленна триггером на очередь сообщений, куда приходят ключи до вырезанных фотографий лиц в объектном хранилище вида:

*/album/myphoto/face0*

Функция tg-webhook должна быть установлена в качестве вебхука для телеграм бота, который будет отправлять вырезанные лица, спрашивая "кто это?".
Этому же боту можно отвечать именем человека на сообщение с его лицом.
Также бот принимает запрос вида "/find name" и возврщает все фотографии с лицом человека с указанным именем.
Стоит заметить, что лица нужно указывать только английскими буквами.

Для обоих функций нужно указать следующие переменные среды:

-'BOT_TOKEN'
-'AWS_ACCESS_KEY_ID'
-'AWS_FOLDER_ID'
-'AWS_SECRET_ACCESS_KEY'
-'BUCKET'

У бота также есть команда "/start" , которая устанавливает собеседника, которому бот будет отправлять вырезанные лица, и от которого будет слушать ответы.
