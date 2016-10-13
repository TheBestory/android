# The Bestory Project
## Android app

**Master branch:** [![Build Status](https://travis-ci.org/thebestory/android.svg?branch=master)](https://travis-ci.org/thebestory/android)  
**Develop branch:** [![Build Status](https://travis-ci.org/thebestory/android.svg?branch=develop)](https://travis-ci.org/thebestory/android)

## Project CV

### Команда

* Октай Татанов / M3236 ([@Oktai15](https://github.com/Oktai15))
* Нариман Сафиулин / M3236 ([@woofilee](https://github.com/woofilee))
* Алексей Анисимов Витальевич / M3237 ([@Bytonaaa](https://github.com/Bytonaaa))

### Идея проекта

**The Bestory** - проект, где люди с разных уголков планеты делятся своими невероятными историями со всем миром!
Cделать англоязычный аналог русского "Подслушано" со значительными улучшениями; сделать проект независимым от соц. сетей.

### Архитектура приложения

#### Минимум

Основная единица взаимодействия в нашем приложении - это **история**.  
Пользователь, впервые запустивший приложение, увидит приветственный экран с кратким экскурсом в функционал приложения.

Главный экран приложения - список историй (stories).  Можно просматривать **последние** опубликованные, **популярные**, **топ**, либо **случайные** истории.  
К понравившейся истории пользователь может поставить **лайк**.  
Каждая история имеет свою **категорию (topic)**. Пользователь может открыть определенную категорию и читать истории, соответствующие только ей.

Каждый пользователь имеет возможность написать и **отправить собственную историю** на модерацию. После чего, ей будет присвоена категория, и она будет опубликована. Все истории публикуются **анонимно**.  

Пользователь может посмотреть список опубликованных им и понравившихся ему историй с данного устройства.

#### Развитие

Смотря в будущее, мы также хотим реализовать такой функционал, как:
* Тёмная тема приложения - пользователю важно иметь возможность комфортно для него читать истории ночью, или при низком освещении, когда яркий экран сильно режет глаза;
* Кеширование - при отсутствии интернет соединения, показывать пользователю пустой экран - не самая удачная идея. Поэтому лучше кешировать некоторую часть историй на главном экране, и в категориях (если они были посещены пользователем);
* Загрузка историй оффлайн - бывают случаи, когда интернет не всегда может быть доступен, например, в метро, или в поездке (временный роуминг, самолёт). Это практически то же самое, что и кеширование, но данный случай предполагает возможность загрузить в кеш сразу определенно большое количество историй, возможно, по некоторым критериям (категория, лайки, дата публикации);
* Система комментирования историй - с помощью них пользователи смогут, например, поддержать автора, или поделиться похожим моментом в своей жизни;
* Систему профилей - таким образом, каждый пользователь сможет посмотреть опубликованные им и понравившиеся ему истории с любого устройства, а также комментировать истории. При регистрации, ему будет предложено "прикрепить" лайки и опубликованные им истории (через данное устройство) к своему профилю, если таковые есть.
* Оффлайн действия - возможность ставить лайки, публиковать комментарии, писать истории - в оффлайн режиме. При подключении к интернету, данные действия будут отправлены на сервер.

### Разработка
Создан Changelog, который можно прочитать [здесь](/CHANGELOG.md). В нём хранятся заметки и скриншоты о процессе разработки, вплоть до кратчайших деталей. После стабильного релиза, данный Changelog будет сконвертирован в привычный Version based Changelog.
