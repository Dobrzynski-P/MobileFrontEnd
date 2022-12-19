package com.example.mobilefrontend;

import java.util.ArrayList;

public class Data {

    public static String url= "http://10.0.2.2:8000/";//Dodaj do Klasy Dane

    public static String dataOd;
    public static String dataDo;
    public static int KateringId=-1;
    public static String days="";
    public static String price="";
    public static String city;
    public static String street;
    public static String kcal;
    public static String postalCode;
    public static String remarks;
    public static String UID;

    public static String UrlGetCena="GET_Katering_cena/";
    public static String UrlGetKateringi="GET_Katering";
    public static String UrlPostZamowienie="POST_Zamowienie/";
    public static String UrlGetKlient="GET_KLIENT";
    public static String UrlPostKlient="POST_klient/";
    public static String UrlGetKateringZamowione="GET_KATERING/";



    public static String[][] StringFromCSV;
    public static ArrayList <String> StringFromCSVArray = new ArrayList<>();
    public static String name;
    public static String Description;
    public static int BasePrice;
}
