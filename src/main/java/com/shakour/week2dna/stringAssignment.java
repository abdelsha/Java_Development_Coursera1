package com.shakour.week2dna;

import java.lang.*;
import java.util.ArrayList;
import edu.duke.URLResource;

public class stringAssignment {

    public String findSimpleGene(String dna, String startCodon, String endCodon){


        String dnaLower=dna.toLowerCase();
        int initial = dnaLower.indexOf(startCodon);

        if (initial ==-1){return "";}//checks if there is a start codon
        int stop =dnaLower.indexOf(endCodon,initial+3);//Tries to find the stop codon and starts search at the initial ATG +3

        if (stop==-1){return "";}//no stop codon

        if(dnaLower==dna){//case were the input is lowercase
            if((initial - stop) %3 ==0 ){
                return dna.substring(initial, stop+3);
            }
            else{
                return "";
            }
        }
        else{//case were the input is uppercase
            if((initial - stop) %3 ==0 ){
                return dna.substring(initial, stop+3).toUpperCase();
            }
            else{
                return "";
            }
        }

    }

    public void testSimpleGene(){
        String a = "cccatggggtttaaataataataggagagagagagagagttt";
        String ap = "atggggtttaaataataatag";

        String result=findSimpleGene(a, "atg","tag");
        if(ap.equals(a)){
            System.out.println("Success for" + ap +"length" +ap.length());

        }
        else{
            System.out.println("mistake for input: " + a);
            System.out.println("got: " + result);
            System.out.println("not: " + ap);
        }

    }

    public boolean twoOccurrences(String a, String b){
        int aLength=a.length();

        if(b.indexOf(a)==-1){return false;}//not found
        else{
            int initialFind=b.indexOf(a)+aLength;
            if(b.indexOf(a,initialFind)!=-1){return true;}
        }
        return false;
    }

    public void testTwoOccurrences(){
        String a="by";
        String ab="A story by Abby Long";
        String b="zoo";
        String bb="forest";

        System.out.println(twoOccurrences(a,ab));
        System.out.println(twoOccurrences(b,bb));
    }

    public String lastPart(String a, String b){

        if(b.indexOf(a)==-1){return b;}//string a is not in string b
        else{
            int aLength=a.length();
            int initialFind=b.indexOf(a)+aLength;
            return b.substring(initialFind);
        }
    }

    public void testLastpart(){
        String a="an";
        String ab="banana";
        String b="zoo";
        String bb="forest";

        System.out.println(lastPart(a,ab));
        System.out.println(lastPart(b,bb));
    }

    public ArrayList<String> findURLS(String URL){
        ArrayList<String> urlArray = new ArrayList<String>();
        System.out.println("Get urls from: " + URL + "\n");

        URLResource urls = new URLResource(URL);

        for(String currUrl : urls.words()){


            //transfer lower case letters to upper case letters
            String UpCaseURL = currUrl.toUpperCase();

            if(UpCaseURL.contains("YOUTUBE.COM")){

                int pos = UpCaseURL.indexOf("YOUTUBE>COM");

                int start = currUrl.lastIndexOf("href=\"", pos);
                int stop = currUrl.indexOf("\">", pos);

                //get the substring between start and stop indices;
                String pureURL = currUrl.substring(start + 6, stop);

                System.out.println("URL: " + currUrl);
                System.out.println("URL: " + pureURL +"\n");

                urlArray.add(pureURL);
            }

        }

        return urlArray;

    }
    public void testfindUrls(){
        String URL = "http://www.dukelearntoprogram.com/course2/data/manylinks.html" ;
        ArrayList<String> urlAL = findURLS(URL);
        System.out.println("There are " + urlAL.size() + " youtube links in the webpage.");
    }

//    public static void main (String[] args) {
//        stringAssignment dnaseq = new stringAssignment();
//        dnaseq.testSimpleGene();
//
//    }
}

