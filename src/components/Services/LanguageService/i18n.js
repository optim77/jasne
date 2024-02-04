// i18n.js
import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';

i18n
    .use(initReactI18next)
    .init({
        resources: {
            en: {
                translation: require('../../../../public/locales/en/translation.json')
            },
            pl: {
                translation: require('../../../../public/locales/pl/translation.json')
            }
        },
        lng: 'en', // default language
        interpolation: {
            escapeValue: false
        }
    });

export default i18n;
