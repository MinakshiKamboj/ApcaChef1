package com.apcachef.model;

import java.util.ArrayList;
import java.util.List;

public class CountryCodePicker {

    private String countryCode;
    private String countryName;

    private List<CountryCodePicker> mCountryList;

    public CountryCodePicker(String countryCode, String countryName) {
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public CountryCodePicker() {

    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public List<CountryCodePicker> getmCountryList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryCodePicker("60", "Malaysia"));
        mCountryList.add(new CountryCodePicker("91", "India"));
        mCountryList.add(new CountryCodePicker("376", "Andorra"));
        mCountryList.add(new CountryCodePicker("971", "United Arab Emirates (UAE)"));
        mCountryList.add(new CountryCodePicker("93", "Afghanistan"));
        mCountryList.add(new CountryCodePicker("1", "Antigua and Barbuda"));
        mCountryList.add(new CountryCodePicker("1", "Anguilla"));
        mCountryList.add(new CountryCodePicker("355", "Albania"));
        mCountryList.add(new CountryCodePicker("374", "Armenia"));
        mCountryList.add(new CountryCodePicker("244", "Angola"));
        mCountryList.add(new CountryCodePicker("672", "Antarctica"));
        mCountryList.add(new CountryCodePicker("54", "Argentina"));
        mCountryList.add(new CountryCodePicker("1", "American Samoa"));
        mCountryList.add(new CountryCodePicker("43", "Austria"));
        mCountryList.add(new CountryCodePicker("61", "Australia"));
        mCountryList.add(new CountryCodePicker("297", "Aruba"));
        mCountryList.add(new CountryCodePicker("358", "Åland Islands"));
        mCountryList.add(new CountryCodePicker("994", "Azerbaijan"));
        mCountryList.add(new CountryCodePicker("387", "Bosnia And Herzegovina"));
        mCountryList.add(new CountryCodePicker("1", "Barbados"));
        mCountryList.add(new CountryCodePicker("880", "Bangladesh"));
        mCountryList.add(new CountryCodePicker("32", "Belgium"));
        mCountryList.add(new CountryCodePicker("226", "Burkina Faso"));
        mCountryList.add(new CountryCodePicker("359", "Bulgaria"));
        mCountryList.add(new CountryCodePicker("973", "Bahrain"));
        mCountryList.add(new CountryCodePicker("257", "Burundi"));
        mCountryList.add(new CountryCodePicker("229", "Benin"));
        mCountryList.add(new CountryCodePicker("590", "Saint Barthélemy"));
        mCountryList.add(new CountryCodePicker("1", "Bermuda"));
        mCountryList.add(new CountryCodePicker("673", "Brunei Darussalam"));
        mCountryList.add(new CountryCodePicker("591", "Bolivia, Plurinational State Of"));
        mCountryList.add(new CountryCodePicker("55", "Brazil"));
        mCountryList.add(new CountryCodePicker("1", "Bahamas"));
        mCountryList.add(new CountryCodePicker("975", "Bhutan"));
        mCountryList.add(new CountryCodePicker("267", "Botswana"));
        mCountryList.add(new CountryCodePicker("375", "Belarus"));
        mCountryList.add(new CountryCodePicker("501", "Belize"));
        mCountryList.add(new CountryCodePicker("1", "Canada"));
        mCountryList.add(new CountryCodePicker("61", "Cocos (keeling) Islands"));
        mCountryList.add(new CountryCodePicker("243", "Congo, The Democratic Republic Of The"));
        mCountryList.add(new CountryCodePicker("236", "Central African Republic"));
        mCountryList.add(new CountryCodePicker("242", "Congo"));
        mCountryList.add(new CountryCodePicker("41", "Switzerland"));
        mCountryList.add(new CountryCodePicker("225", "Côte D'ivoire"));
        mCountryList.add(new CountryCodePicker("682", "Cook Islands"));
        mCountryList.add(new CountryCodePicker("56", "Chile"));
        mCountryList.add(new CountryCodePicker("237", "Cameroon"));
        mCountryList.add(new CountryCodePicker("86", "China"));
        mCountryList.add(new CountryCodePicker("57", "Colombia"));
        mCountryList.add(new CountryCodePicker("506", "Costa Rica"));
        mCountryList.add(new CountryCodePicker("53", "Cuba"));
        mCountryList.add(new CountryCodePicker("238", "Cape Verde"));
        mCountryList.add(new CountryCodePicker("599", "Curaçao"));
        mCountryList.add(new CountryCodePicker("61", "Christmas Island"));
        mCountryList.add(new CountryCodePicker("357", "Cyprus"));
        mCountryList.add(new CountryCodePicker("420", "Czech Republic"));
        mCountryList.add(new CountryCodePicker("49", "Germany"));
        mCountryList.add(new CountryCodePicker("253", "Djibouti"));
        mCountryList.add(new CountryCodePicker("45", "Denmark"));
        mCountryList.add(new CountryCodePicker("1", "Dominica"));
        mCountryList.add(new CountryCodePicker("1", "Dominican Republic"));
        mCountryList.add(new CountryCodePicker("213", "Algeria"));
        mCountryList.add(new CountryCodePicker("593", "Ecuador"));
        mCountryList.add(new CountryCodePicker("372", "Estonia"));
        mCountryList.add(new CountryCodePicker("20", "Egypt"));
        mCountryList.add(new CountryCodePicker("291", "Eritrea"));
        mCountryList.add(new CountryCodePicker("34", "Spain"));
        mCountryList.add(new CountryCodePicker("251", "Ethiopia"));
        mCountryList.add(new CountryCodePicker("358", "Finland"));
        mCountryList.add(new CountryCodePicker("679", "Fiji"));
        mCountryList.add(new CountryCodePicker("500", "Falkland Islands (malvinas)"));
        mCountryList.add(new CountryCodePicker("691", "Micronesia, Federated States Of"));
        mCountryList.add(new CountryCodePicker("298", "Faroe Islands"));
        mCountryList.add(new CountryCodePicker("33", "France"));
        mCountryList.add(new CountryCodePicker("241", "Gabon"));
        mCountryList.add(new CountryCodePicker("44", "United Kingdom"));
        mCountryList.add(new CountryCodePicker("1", "Grenada"));
        mCountryList.add(new CountryCodePicker("995", "Georgia"));
        mCountryList.add(new CountryCodePicker("594", "French Guyana"));
        mCountryList.add(new CountryCodePicker("233", "Ghana"));
        mCountryList.add(new CountryCodePicker("350", "Gibraltar"));
        mCountryList.add(new CountryCodePicker("299", "Greenland"));
        mCountryList.add(new CountryCodePicker("220", "Gambia"));
        mCountryList.add(new CountryCodePicker("224", "Guinea"));
        mCountryList.add(new CountryCodePicker("450", "Guadeloupe"));
        mCountryList.add(new CountryCodePicker("240", "Equatorial Guinea"));
        mCountryList.add(new CountryCodePicker("30", "Greece"));
        mCountryList.add(new CountryCodePicker("502", "Guatemala"));
        mCountryList.add(new CountryCodePicker("1", "Guam"));
        mCountryList.add(new CountryCodePicker("245", "Guinea-bissau"));
        mCountryList.add(new CountryCodePicker("592", "Guyana"));
        mCountryList.add(new CountryCodePicker("852", "Hong Kong"));
        mCountryList.add(new CountryCodePicker("504", "Honduras"));
        mCountryList.add(new CountryCodePicker("385", "Croatia"));
        mCountryList.add(new CountryCodePicker("509", "Haiti"));
        mCountryList.add(new CountryCodePicker("36", "Hungary"));
        mCountryList.add(new CountryCodePicker("62", "Indonesia"));
        mCountryList.add(new CountryCodePicker("353", "Ireland"));
        mCountryList.add(new CountryCodePicker("972", "Israel"));
        mCountryList.add(new CountryCodePicker("44", "Isle Of Man"));
        mCountryList.add(new CountryCodePicker("354", "Iceland"));
        mCountryList.add(new CountryCodePicker("246", "British Indian Ocean Territory"));
        mCountryList.add(new CountryCodePicker("964", "Iraq"));
        mCountryList.add(new CountryCodePicker("98", "Iran, Islamic Republic Of"));
        mCountryList.add(new CountryCodePicker("39", "Italy"));
        mCountryList.add(new CountryCodePicker("44", "Jersey "));
        mCountryList.add(new CountryCodePicker("1", "Jamaica"));
        mCountryList.add(new CountryCodePicker("962", "Jordan"));
        mCountryList.add(new CountryCodePicker("81", "Japan"));
        mCountryList.add(new CountryCodePicker("254", "Kenya"));
        mCountryList.add(new CountryCodePicker("996", "Kyrgyzstan"));
        mCountryList.add(new CountryCodePicker("855", "Cambodia"));
        mCountryList.add(new CountryCodePicker("686", "Kiribati"));
        mCountryList.add(new CountryCodePicker("269", "Comoros"));
        mCountryList.add(new CountryCodePicker("1", "Saint Kitts and Nevis"));
        mCountryList.add(new CountryCodePicker("850", "North Korea"));
        mCountryList.add(new CountryCodePicker("82", "South Korea"));
        mCountryList.add(new CountryCodePicker("965", "Kuwait"));
        mCountryList.add(new CountryCodePicker("1", "Cayman Islands"));
        mCountryList.add(new CountryCodePicker("7", "Kazakhstan"));
        mCountryList.add(new CountryCodePicker("856", "Lao People's Democratic Republic"));
        mCountryList.add(new CountryCodePicker("961", "Lebanon"));
        mCountryList.add(new CountryCodePicker("1", "Saint Lucia"));
        mCountryList.add(new CountryCodePicker("423", "Liechtenstein"));
        mCountryList.add(new CountryCodePicker("94", "Sri Lanka"));
        mCountryList.add(new CountryCodePicker("231", "Liberia"));
        mCountryList.add(new CountryCodePicker("266", "Lesotho"));
        mCountryList.add(new CountryCodePicker("370", "Lithuania"));
        mCountryList.add(new CountryCodePicker("352", "Luxembourg"));
        mCountryList.add(new CountryCodePicker("371", "Latvia"));
        mCountryList.add(new CountryCodePicker("218", "Libya"));
        mCountryList.add(new CountryCodePicker("212", "Morocco"));
        mCountryList.add(new CountryCodePicker("377", "Monaco"));
        mCountryList.add(new CountryCodePicker("373", "Moldova, Republic Of"));
        mCountryList.add(new CountryCodePicker("382", "Montenegro"));
        mCountryList.add(new CountryCodePicker("590", "Saint Martin"));
        mCountryList.add(new CountryCodePicker("261", "Madagascar"));
        mCountryList.add(new CountryCodePicker("692", "Marshall Islands"));
        mCountryList.add(new CountryCodePicker("389", "Macedonia (FYROM)"));
        mCountryList.add(new CountryCodePicker("223", "Mali"));
        mCountryList.add(new CountryCodePicker("95", "Myanmar"));
        mCountryList.add(new CountryCodePicker("976", "Mongolia"));
        mCountryList.add(new CountryCodePicker("853", "Macau"));
        mCountryList.add(new CountryCodePicker("1", "Northern Mariana Islands"));
        mCountryList.add(new CountryCodePicker("596", "Martinique"));
        mCountryList.add(new CountryCodePicker("222", "Mauritania"));
        mCountryList.add(new CountryCodePicker("1", "Montserrat"));
        mCountryList.add(new CountryCodePicker("356", "Malta"));
        mCountryList.add(new CountryCodePicker("230", "Mauritius"));
        mCountryList.add(new CountryCodePicker("960", "Maldives"));
        mCountryList.add(new CountryCodePicker("265", "Malawi"));
        mCountryList.add(new CountryCodePicker("52", "Mexico"));
        mCountryList.add(new CountryCodePicker("258", "Mozambique"));
        mCountryList.add(new CountryCodePicker("264", "Namibia"));
        mCountryList.add(new CountryCodePicker("687", "New Caledonia"));
        mCountryList.add(new CountryCodePicker("227", "Niger"));
        mCountryList.add(new CountryCodePicker("672", "Norfolk Islands"));
        mCountryList.add(new CountryCodePicker("234", "Nigeria"));
        mCountryList.add(new CountryCodePicker("505", "Nicaragua"));
        mCountryList.add(new CountryCodePicker("31", "Netherlands"));
        mCountryList.add(new CountryCodePicker("47", "Norway"));
        mCountryList.add(new CountryCodePicker("977", "Nepal"));
        mCountryList.add(new CountryCodePicker("674", "Nauru"));
        mCountryList.add(new CountryCodePicker("683", "Niue"));
        mCountryList.add(new CountryCodePicker("64", "New Zealand"));
        mCountryList.add(new CountryCodePicker("968", "Oman"));
        mCountryList.add(new CountryCodePicker("507", "Panama"));
        mCountryList.add(new CountryCodePicker("51", "Peru"));
        mCountryList.add(new CountryCodePicker("689", "French Polynesia"));
        mCountryList.add(new CountryCodePicker("675", "Papua New Guinea"));
        mCountryList.add(new CountryCodePicker("63", "Philippines"));
        mCountryList.add(new CountryCodePicker("92", "Pakistan"));
        mCountryList.add(new CountryCodePicker("48", "Poland"));
        mCountryList.add(new CountryCodePicker("508", "Saint Pierre And Miquelon"));
        mCountryList.add(new CountryCodePicker("870", "Pitcairn Islands"));
        mCountryList.add(new CountryCodePicker("1", "Puerto Rico"));
        mCountryList.add(new CountryCodePicker("970", "Palestine"));
        mCountryList.add(new CountryCodePicker("351", "Portugal"));
        mCountryList.add(new CountryCodePicker("680", "Palau"));
        mCountryList.add(new CountryCodePicker("595", "Paraguay"));
        mCountryList.add(new CountryCodePicker("974", "Qatar"));
        mCountryList.add(new CountryCodePicker("262", "Réunion"));
        mCountryList.add(new CountryCodePicker("40", "Romania"));
        mCountryList.add(new CountryCodePicker("381", "Serbia"));
        mCountryList.add(new CountryCodePicker("7", "Russian Federation"));
        mCountryList.add(new CountryCodePicker("250", "Rwanda"));
        mCountryList.add(new CountryCodePicker("966", "Saudi Arabia"));
        mCountryList.add(new CountryCodePicker("677", "Solomon Islands"));
        mCountryList.add(new CountryCodePicker("248", "Seychelles"));
        mCountryList.add(new CountryCodePicker("249", "Sudan"));
        mCountryList.add(new CountryCodePicker("46", "Sweden"));
        mCountryList.add(new CountryCodePicker("65", "Singapore"));
        mCountryList.add(new CountryCodePicker("290", "Saint Helena, Ascension And Tristan Da Cunha"));
        mCountryList.add(new CountryCodePicker("386", "Slovenia"));
        mCountryList.add(new CountryCodePicker("421", "Slovakia"));
        mCountryList.add(new CountryCodePicker("232", "Sierra Leone"));
        mCountryList.add(new CountryCodePicker("378", "San Marino"));
        mCountryList.add(new CountryCodePicker("221", "Senegal"));
        mCountryList.add(new CountryCodePicker("252", "Somalia"));
        mCountryList.add(new CountryCodePicker("597", "Suriname"));
        mCountryList.add(new CountryCodePicker("211", "South Sudan"));
        mCountryList.add(new CountryCodePicker("239", "Sao Tome And Principe"));
        mCountryList.add(new CountryCodePicker("503", "El Salvador"));
        mCountryList.add(new CountryCodePicker("1", "Sint Maarten"));
        mCountryList.add(new CountryCodePicker("963", "Syrian Arab Republic"));
        mCountryList.add(new CountryCodePicker("268", "Swaziland"));
        mCountryList.add(new CountryCodePicker("1", "Turks and Caicos Islands"));
        mCountryList.add(new CountryCodePicker("235", "Chad"));
        mCountryList.add(new CountryCodePicker("228", "Togo"));
        mCountryList.add(new CountryCodePicker("66", "Thailand"));
        mCountryList.add(new CountryCodePicker("992", "Tajikistan"));
        mCountryList.add(new CountryCodePicker("690", "Tokelau"));
        mCountryList.add(new CountryCodePicker("670", "Timor-leste"));
        mCountryList.add(new CountryCodePicker("993", "Turkmenistan"));
        mCountryList.add(new CountryCodePicker("216", "Tunisia"));
        mCountryList.add(new CountryCodePicker("676", "Tonga"));
        mCountryList.add(new CountryCodePicker("90", "Turkey"));
        mCountryList.add(new CountryCodePicker("1", "Trinidad &amp; Tobago"));
        mCountryList.add(new CountryCodePicker("688", "Tuvalu"));
        mCountryList.add(new CountryCodePicker("886", "Taiwan"));
        mCountryList.add(new CountryCodePicker("255", "Tanzania, United Republic Of"));
        mCountryList.add(new CountryCodePicker("380", "Ukraine"));
        mCountryList.add(new CountryCodePicker("256", "Uganda"));
        mCountryList.add(new CountryCodePicker("1", "United States"));
        mCountryList.add(new CountryCodePicker("598", "Uruguay"));
        mCountryList.add(new CountryCodePicker("998", "Uzbekistan"));
        mCountryList.add(new CountryCodePicker("379", "Holy See (vatican City State)"));
        mCountryList.add(new CountryCodePicker("1", "Saint Vincent &amp; The Grenadines"));
        mCountryList.add(new CountryCodePicker("58", "Venezuela, Bolivarian Republic Of"));
        mCountryList.add(new CountryCodePicker("1", "British Virgin Islands"));
        mCountryList.add(new CountryCodePicker("1", "US Virgin Islands"));
        mCountryList.add(new CountryCodePicker("84", "Vietnam"));
        mCountryList.add(new CountryCodePicker("678", "Vanuatu"));
        mCountryList.add(new CountryCodePicker("681", "Wallis And Futuna"));
        mCountryList.add(new CountryCodePicker("685", "Samoa"));
        mCountryList.add(new CountryCodePicker("383", "Kosovo"));
        mCountryList.add(new CountryCodePicker("967", "Yemen"));
        mCountryList.add(new CountryCodePicker("262", "Mayotte"));
        mCountryList.add(new CountryCodePicker("27", "South Africa"));
        mCountryList.add(new CountryCodePicker("260", "Zambia"));
        mCountryList.add(new CountryCodePicker("263", "Zimbabwe"));
        return mCountryList;
    }

}


