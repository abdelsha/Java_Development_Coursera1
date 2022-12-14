package com.shakour.week2dna;

public class findGene {
    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex +3);
        while (currIndex !=-1){
            if((currIndex-startIndex)%3 ==0){
                return dna.substring(startIndex,currIndex+3);
            }
            else{
                currIndex=dna.indexOf("TAA", currIndex+1);
            }
        }
        return "";
    }

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon,startIndex+3);

        while (currIndex!=-1){
            int diff = currIndex - startIndex;
            if (diff %3 ==0){
                return currIndex;
            }
            else{
                currIndex=dnaStr.indexOf(stopCodon,currIndex+1);
            }
        }
        return -1;
    }
    public String findGene2(String dna, int where){
        String[] codons={"TAA","TAG","TGA"};
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex==-1)return "";
        int minDnaLength=dna.length();
        int endCodonIndex=dna.length();

        for(String codon: codons){//this is a for each => doesn't depend on the index.
            int endCodon = findStopCodon(dna,startIndex,codon); // finds the valid endcodon index

            if(endCodon ==-1)continue;//if there is no end codon, then skip the instructions below

            int length = endCodon-startIndex; //calculates the length of the complete dna sequence
            if(length<minDnaLength){ //if the sequence is smaller than the current minimum sequence
                minDnaLength=length; //update the new minimum
                endCodonIndex=endCodon; //update the new endcodon index.
            }
        }

        if (minDnaLength==dna.length()){
            return "";
        }
        else{
            return dna.substring(startIndex,endCodonIndex+3);
        }

    }
    public int findAllGenes(String dna){
        int startIndex= 0;
        int count=0;
        int longest=0;
        while (true){
            String currGene = findGene2(dna,startIndex);
            if(currGene.isEmpty())break;
            System.out.println(currGene);
            startIndex= dna.indexOf(currGene,startIndex)+currGene.length();
            if(currGene.length()>longest){
                longest=currGene.length();
            }
        }

        return longest;
    }
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
            return dna;
        }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }

    public int rep(String dna){
        int index=0;
        int count =0;
        while(dna.indexOf("CTG",index+3)!=-1){
            int currIndex=dna.indexOf("CTG",index);
            count+=1;
            index=dna.indexOf("CTG",currIndex+3);
        }

        return count;
    }


    public void testFindGene(){
        String dna= "ATGCCCGGGAAATAACCC";
        int gene = rep("AACCCTAACCCTAACCCTAACCCTAACCCTAACCCTA" +
                "ACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCT" +
                "AACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCT" +
                "AACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCC" +
                "TAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTA" +
                "ACCCTAACCCTAACCCTAACCCTTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTC" +
                "ACCCTTCTAACTGGACTCTGACCCTGATTGTTGAGGGCTGCAAAGAGGAAGAATTTTATTTACCGTCGCTGTGGCCCCGA" +
                "GTTGTCCCAAAGCGAGGTAATGCCCGCAAGGTCTGTGCTGATCAGGACGCAGCTCTGCCTTCGGGGTGCCCCTGGACTG" +
                "CCCGCCCGCCCGGGTCTGTGCTGAGGAGAACGCTGCTCCGCCTCCGCGGTACTCCGGACATATGTGCAGAGAAGAACGCA" +
                "GCTGCGCCCTCGCCATGCTCTGCGAGTCTCTGCTGATGAGAACACAGCTTCACTTTCGCAAAGGCGCAGCGCCGGCGCA" +
                "GGCGCGGAGGGGCGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCCCGAACCCGAACCCTAATGCCGTCATAAGAGCCC" +
                "TAGGGAGACCTTAGGGAACAAGCATTAAACTGACACTCGATTCTGTAGCCGGCTCTGCCAAGAGACATGGCGTTGCGGTG" +
                "ATATGAGGGCAGGGGTCATGGAAGAAAGCCTTCTGGTTTTAGACCCACAGGAAGATCTGTGACGCGCTCTTGGGTAGAGC" +
                "ACACGTTGCTGGGCGTGCGCTTGAAAAGAGCCTAAGAAGAGGGGGCGTCTGGAAGGAACCGCAACGCCAAGGGAGGGTGT" +
                "CCAGCCTTCCCGCTTCAACACCTGGACACATTCTGGAAAGTTTCCTAAGAAAGCCAGAAAAATAATTTAAAAAAAAATCC" +
                "AGAGGCCAGACGGGCTAATGGGGCTTTACTGCGACTATCTGGCTTAATCCTCCAAACAACCTTGCCATACCAGCCCATCA" +
                "GTCCTCTGAGACAGGTGAAGAACCTGAGGTCGCAGGAGGACACCCAGAAGGTCCAGAGAGAGCCTCCTAGGCCCCCCACC" +
                "TCCCCCCGTGGCAGCTCCAACCCCAGCTTTTTCACTAGTAAGGCAGTCGGGCCCCTGGGCCACGCCCACTCCCCCAAGCG" +
                "GGGAAGGAGCTTCGCGCTGCCGCTTGGCTGGGGACTGGGCACCGCCCTCCCGCGGCTCCTGAGCCGGCTGCCACCAGGGG" +
                "GCGCGCCAGCGGTGTCCGGGAGCCTAGCGGCGCGTGTGCAGCGGCCAGTGCACCTGCTCTGGCCCTCGCCGCGGTCTCTG" +
                "CCAGGACCCCGACGCCCAGCCTGACCCTGCCATTCAGCGGGGCTGCGGCTCCACGGCCTGCGACAGCAGCCCCACCTGGC" +
                "ATTCAGCGCGCTCCCGGGGGCAGAGGTCGCGGTGTCCTCACGCTGTGGTGCCGGCCTACAACCCCCACGCCGGGCTCGGG" +
                "CCCGGCGGAGGAGGGCGATGCTCCCCGGGTAGGACAAACCGGTCACCTGGGCTGCGACGGCGGCTTAGGGGCAGAAGCGG" +
                "CGGTCCAGGGCCGCCTGGCGCAGCAGCCTGTCCCAGCCGCGGTCCCTGCAGTCCCTCCCTGGCGGCTGCGCAGCCGTCCC" +
                "ACGACAGGGGCCATAAACTCTCCAGAGCGGAAAGCCGCACCCTGGTGGCCCGGCCCCGCGCCCAGACCTGGCGGCCGCTG" +
                "GCACCTGACCCGCTGCATGGGTCTCCAGGGAGCTCGCTGCCCACCCGGCGCTGCAGGCTCGGCTCCCTCGTACACTCTCT" +
                "GGTAGGTGCTAGGGACGACCCTATGGGCCAGCTTGCCATGCCCAGTCCCCAGGCCGCACCCACCCTGGCTCCCTGGGCTA" +
                "GGGGACTGGCTCCTCCTGTGAGTCGTGGGTCTGGGAGGCAGGGGCGTTAGGGGAGAGTGAGGGACCGAGGGCAGCCCCTG" +
                "CTGTGTGCACAGCGAGGTCGTGCACAGGCGTCTGTTGCAGAGCGTGCAGCTTCAGATGAGACTGGATTGCAGGTGGAGATGACTGTGGGTGCGCACACCTGG" +
                "AGGTGAAGGGGAGGCAGCCTGTCTACCTGACCCATGAAATACAGGAGACTGTACCCCAGAAGCAGCGGGTTCACTGCTCCATTGATTAAGCAAGTCTGGGACA" +
                "CACATGTAGCTAAGCTGTGAGTTCTGTACCAGCGATCCCAACACCCACGCCCTCAGAAAGACACTGGTGTGGGGCCTGGGTGCTTGTCAGGCCTGAAAGTGGA" +
                "GAGCACGGGCCAGAGACACTGAGTAGGGGGAACCCACCCTAGGGCTCTGAGGGACGACGATGTGGGGAGCTGGTGACAGAGCCTGAGCTGGCCCAATGTTGCA" +
                "CGGTGGGGACAGATTCGAGGTACAGTGGGGACTGGTGACCTCAGTTCCCAGTGTCCCAGCCTGGCCTCCCAGTCCACCCAGCAATTAGTGGGTGCTGCCCTGC" +
                "AAAGACTCTGGGGGTGCCTCAGCCCTCCTCATCACACGTGACTGGTGACTTCTGTGTCCACCCGCACAATAAGAGGGATCTTCTCTCACTTTCAGGCAAGCCC" +
                "AAGAAAGTCAGGGGCCTATGTGAGCCAAAGAGGAGAGAAGGTGATGCCTCAGCCCAGTGTTTCTGCCCCACCTCGCTTGTGGCCTTCGGAACTTGATTTGCAC" +
                "CGCAGGAAAATGGGCAATGAAAACCCCTCCCTAACTGGCTTCTCAGTCCACTCTGACCAGCCCACTGCACAGCGCCCACCCTGCAGCTCCAGGTACAGAGGCT" +
                "GGGATGGCTCTGGGCTGACCTAAGGGCCTTCTGATGGCTCCAACCCTCGGGATGCCTCATGCTCACCCTTTGGCACCCACCTGACAGCTCAGCATCTCTGCTC" +
                "TCTGCCATCCTCAATGCCTGCTCTAGACAAGCCCAAGTCCCCCAGGAGTGGCAGAGGGAACTGAGCCGAAAACTAAGTCTCGGCTCACTGAACCCCAAGTGGG" +
                "CTGTCCAGCCTCGCCCTTCAGTTCACAACCCCAGGCAGGTTCCCTCCAGGGATGTGATCCCAGGGGCCACAGCAGCACATTCTGGCCTAACCTATCCACTATT" +
                "TAAACAGTTACTGAAAAGGCCAGGATGGCCGTGGGCCCTGACATTAATCCCCTTTCTCTGTGAGGGGGCTGGGTTGGGTTTGCCATCCTGATGTCTTTGTGGA" +
                "AAGAGCTGGCAGGTGAAGCAAGTCTCAGGGGCCAGCCATGGGACAAGGAACCTAGGACTGGCCTCTGCTGGAACCCTCTGAGGCCCCTGCGGACAGGAGGATC" +
                "CAATGGAGGTCTAGCCACCCCTCCCAGGTTGGTGCTCACAGCCCCTCCCTGGCCCACTCCCTGCACACCTGCACCTGCTGGTCTCTGGGAGAGGAGCATCCAT" +
                "CCATCTTGTGCGCATAGCTTTCGGCTCCATTTTCATGAGGATGGTCTCCTTGGCAGAAATGCCCATTAGGGGATCCTGAGCCTGTGCTAGCTCTTCTCTAAGT" +
                "GCCAAAGCCAGTGAGAGGGACTTGAAAACTCAAGACTTATTAACAGTATTTTCTGCATTTTGTGCTTTCAGGGTTGTTTTTTCCTTAAAATGTGTAAAAACAA" +
                "ACATTGAGATTTCTATCTTTTATATAATTTGGATTCTGTTATCACACGGACTTTTCCTGAAATTTATTTTTATGTATGTATATCAAACATTGAATTTCTGTTT" +
                "TCTTCTTTACTGGAATTGTTAACTGTTTTATAGGCCAAATCTTTTAAAAAAAACACATCTCTCTAATTTCTCTAAACATTTCTAATTACATATATATTTACTA" +
                "TACCTAATACACTACTTTGGAATTCCTTGAGGCCTAAATGCATCGGGGTGCTCTGGTTTTGTTGTTGTTATTTCTGAATGACATTTACTTTGGTGCTCTTTAT" +
                "TTTGCGTATTTAAAACTATTAGATCGTGTGATTATATTTGACAGGTCTTAATTGACGCGCTGTTCAGCCCTTTGAGTTCGGTTGAGTTTTGGGTTGGAGAATT" +
                "TTCTTCCACAAGGGATTGTCTTGGATTTTTCTGTTTCTCCCTCAATATCCACCTGGAAAACATTTCAATTAATTTATATTTACTTAAATATTTCTGTGCAAAA" +
                "ACTGTGTACAAAAGCCCCAAAGCATAATTTGTGCAGTTGAGCGCATGTTCTGTTGTTCAGCATTTATGGTGGTTGGTAGTGGAAAAGATTTTTAGAATATGTG" +
                "GATTTTCGGGATATTCCCAGAAGCCCAGATAGCGACACTTTACCTTTGGAGGAATTACTTCTCAGAATATTGCACACAATCAATCGCCTTTGGAAGGAGCATA" +
                "TATCCCCAGCAAAAGCTCTGGTTTTTTGAAGTCTGTATTGTGTGTTACTTCCAGGAGAATATGCAATGATGACAATGTTATTAGATGATTCAAATATGAAGTG" +
                "CTGTTATGCCAAACAATGAATCTTTGTGTTATACATTATGCCTAACTATAAATCTTTGTGTTATACATTTTAATGTCATTGGAGAGTACTCCTGTCTTCTTGG" +
                "CATTATTGATAATTAGATTCTAATTGCTAATAAGTCAGAAAAATTAGGAACACCAAATTTCAGTTGTCTCAAAAGCACTCCTCTTATTAAATTTGGATGTTTA" +
                "CCTTTATCACATCAAAAGAAATATTGTTAGAAAGGTGTTTAATGTTTTGCAGATGGATAGATTACTGTTATTAGTTCTCATTTCATTGTTAATTTTTAAAACC" +
                "ATAAGGTTGGAAGTATCAATATGCCTTTCAATATACCTTAGTGGAATTTATTAAATTTTCATGGATGTCCTTTAGGGGGTTCAGGAAGTTATTTCTATTGCTA" +
                "GATTTCTGGAAGATTTATCAGGAATGAGTGTCAGACATTGTCAGACGTCCATTGAAATCATCATGGTCTTTTCCTTTATTCTATTAATATGATGTATTACACT" +
                "GATTGATTTTTAAATTTGTATTGGTAGGATAATTCCACTTGGTTATATTGTCTAACTTTTTTCTAATTTTCTTTCATTTTTATTACAGATGAGGCCTCACTCT" +
                "GTCACCCAGGTTGGGGTGGAGTGGCACAGTCACAGCTCACTATAACCTCAAGCTCCTGGGCTCAAGTGATCCTGCCACCTCAGCCTCCTAAGTAGCTGGAACT" +
                "ACAGATGTGCACTGCCATGCCAGGCTTGTCTAACATTTTTATGTGTTGCTTCATCCAGTTTGCTAGAGTTTTTGGAGATTTCTGTCTTCATTCATGAGGGATA" +
                "ATAGTCTGCACTTTTATTTTCTTGTGATACTTTTGTCTGATTTGTTATCTGGGTAATACTGGCCTTGAAAATGAATTGATGTTTTCCTGCTTCTCTGCTTTGC" +
                "AAGTGTTTGTGAAGGATTGGTTATTCATTAAGTGTTTAATAGAATTCACTAGTGAAGCTATGTGAGCCAGGGCTAGACTGATGAAGAGTTTTCATTAGTCTAA" +
                "TCTGTTTACTTGCTGTATAAGTACGCATATATTCTCTTTCTTCTTGATTTAATTTTACACTTTGTGTATAGCAGGGAATCTGTGTCTAATTTGTAGTATTTCA" +
                "TGCTTCTAGGTTTTCATGGCAGTTGAGATGTAAGAATAACAATAATGTTGGGAGAAGGAAGTTGTGGACAATCCATGAATATCCCAACATCTGTTGTAGGAAG" +
                "GTTAAGATTACTTTTTTTTTTTTTGCTGTACTGAACTGAATACTCTTATTTATAATGTCAGACAAATGTAATGTTGTATATAAATAGAACTAGGAAAATGTGC" +
                "CATTTGTCTTAGTATTTAATCAAGATGGAAGTCTGGGCCTACCTCCTCTCTTTTATTAATATGTAGACAGGACACCAACACAAATTAGAATGAAGACAAACAA" +
                "AATGTTAGCAAATGAAGAATGGTATCAATTGGTTAAAATGTGATGAAATAGAGTGGTGAATATTTACATAGAATCCATGATGTGTTAGGTGCTATTTCAAGCT" +
                "ATTTGCACATATAGTTTTAATACCAATGACGTTAAAATGTATAACACAAAGATTCATATAAATAAAAATTACAACATTGTAAATAATATTAGGTGACACTAAA" +
                "ACTGTCATAGAAATACACATTTATATAAAACATAAAGTAACATGAAGTATTAAATTTTAGAAACTTTGATTACTAATCAGATGAACAACTGATTAGCCTTTTT" +
                "ATCCAGTAAAAAAGGCATACATATTATTTTCAAATTCCAGAGACAAATATTTTAAATATTGAAGTTGAAGACCTAAAAATGTGTCACTGACCTCATGGAAGTA" +
                "GATATTCACTAGGTGATATTTTCTAGGCTCTCTGAAATTATATCAGAAAAATGTGAATTAGAATATAACCCATAAATAATATCTGGCCACATACAAAGTAATT" +
                "GAAGATCAATTTAAATGGCTATTGGATTAAGAAATAGGGACTGAGGTAAATTTGCAGTGTCAGGGAGGATCTAAGGAGGAAGCATTGACACTGGAGCCCAAGG" +
                "ACCTGGGATCACAGAACAGATTCTACCAGTGCTAACTTACTGCTCCACAGAAAACATCAATTCTGCTCATGCGCAGGTACAATTCATCAAGAAAGGAATTACA" +
                "ACTTCAGAAATGTGTTCAAAATATATCCATACTTTGACATATTAATGAAGTAATCACATTCTACACATAACTACTCCATATGGAATACTGGGGAGGAGGTGTT" +
                "CCAAATAAAGAGACTGAGGATTTCTCATGAGAACTCAGTGTCTGCTAGAAAATATCTAAGTAAAATATTTTACTTATGTGGAAAGTGTGGATGTTTGTGCATC" +
                "AAAAGTTTCAAGAATCCCTAAAATTTACAATGGAGATGAGGAGAAAATATCAGAATTTCCCAGCACCAGAAATAAGGCAAGAAAAAATTCAGAGGGGTTGTAA" +
                "ATGTGAAAAGCCAATGGCTGGTCACACAGCAACATTGATAACCTTGTGCCTGGACAACTAGAATAAATACATAAACATACACATTGAAAATATTTCCAATATT" +
                "AGATCTCCCTCATGTGAGAACTAAATTATAAAGATTGAAGCATAGAAGAAAATAAGCTACCAGAATAAATTTGATTACACATAAATTTCTGATATTGAAACTG" +
                "TCACAAATGTTTAAGTTGGTAGTGGAAGACAAAGGACATATAATCTTGGGAGTCCTAAGGCCCTGCCCACTGCCAGTCCCTCCACACTACTACAGCTGATGC" +
                "TTTCTGGAAATCACCACCTCCTGGCAGGAGCCCAACCAGCACAAATATAGAGCATTAAACCACCAAAGCTAAGGAGGCTCACAGAGTCTATTGCACCCTTC" +
                "ACCACCTCCACTGGAACAGGCGCTGGTATCCATGGCTCAGAGACCCAAAGATGGTTCACATCACAGGGCTCTATGCAGACAACCCCCAGTACCAGCCCAAAGC" +
                "CACGTAGACCTGCTGGGTGGCTAGACCCAGAAGAGAGACAACAATCAATGCACTTTGGCTTACAGGAAGCCATGCCCATAGGAAAAAGGGGAGAGTACTACGT" +
                "CAAGGGAACACCCCGTGGGATGAAAGAGTCTGAACAACAGTCTTCAGCCCTAGACCTTTCCTCTGACAGAGTCTACCAAAATGAGAAGGAACCAGAAAACCAA" +
                "CCCTGGTAATCTGACAAAACAAGAATCTTCAACACCCCCCAAAAAATCACACCAGTTCATCACCAATGGATCCAAACAAAGAAGAAATCACTGATTCATCTAA" +
                "AAAAAAATTCAGGTTAGTTATTAAGCTAATCAGGGAGGGGCCAGAGAAAGATGAAGCCCAATGCAAGAAAATCCAAAAAATGATACAATACGTGAAGGGAGAA" +
                "TTATTCAAGGAAATAGATAGCTTAAATAAAAAAATAAAAAATCAGGAAACTTTGGACGTACTTTTAGAAATGTGAAATGCTCTGGAAAGTCTCAGCAATAGAA" +
                "TTGAACAAGTAGAAGAAAGAAATTCAGAATTCGAAGACAAGGTCTTTGATTTAACCCAATCCAATAAAGACAAAGAAAAAAGAATAAGAAAATATGAGCAAAG" +
                "TCTCCAAGGAGTCTGGCATTCTGTTAAATGATGAAACCTAACACTAATTGGTGTACCTGAGGAAGAAGTGAATTCTAAAAGCCAGGAAAACATATTTGGGAGA" +
                "ATAATCTAGGAAAACTTCCATGGCCTTGTGAGAGACCTAGACATCCAAATACAAGAACCACAAATAACACCTGGGAAATTCATCACAAAAAGATCTTAGCCTA" +
                "GGCACATTGTCATTAGGTTATCCAAAGTTAAGACAAAGGAAAGAATCTTAAGAGCTGTGAGACAGAAGCACTAGGTAACCTATAAAGGAAAACCTGTCAAATT" +
                "AACAGCAGATTTCACAGCAGGAACCTTACAAGCTAGATGGGATTGGGGCCCTTTCTTCAGCCTCCTCAAACAAAACAATTATCAGCCAAGAATTTTGTATCCA" +
                "GCAAAACTAAACATCATATATGAAGGAAAGATACAGTCATTTTCAGACAAACAAATGCTGACAGAATTTGCCATTACCAAGCCAGGACTCTAAGAACTGCTAA" +
                "AAGGAGCTCTAAATCATGAAACAAATCCTGGAAACACATCAAAACAGAACTTCATTAACGCATAAATCACACAGGACCTATAAAACAAAAATACAAGTTAAAA" +
                "AACAAAAACAAAGTACAGAGGCAACAAAGAGCATGATGAAAGCAATGGTACCTCACTTTTTAATACTAATGTTGGTTGTAAATGGCTTAAATGCTCCACTTAC" +
                "AAGATACAGAACCACAGAATGGATAACAACTCACCAACTAACTATCTGCTGCCTTCAGGAGACTCACCTAACACATAACGACTTACATAAACTTAAGGAAAGT" +
                "GGTAGAAAAAGGCATTTCATGCAAATGGACACCAAAAGCAAGCAGCAGTAACTATTCTCATATGAGACAAAACAAACTTTAAAGCAACAGTAGCTAAAAGAGA" +
                "CAAAGAGAGACAGTATATCATCTGTCACCTGACAGTCTCATCCAACAGAAAAATATGACAATCCTAAACATATGTGAACCTAACACTGGAGCTCCCAAATTTA" +
                "TAAAACAATTACTAGTAGACATAAGAAATAAGATAGACAGCAACACAATAATAGTGGGGGACTTCAATACTCCACTGACAGCACTAGACAGGTCATCAAGACA" +
                "GAAAGTCAACAAAGAAACAATGGATTTAAACTATACTTTGGAACAAATGGACTTAACAGATATATATAGAACATTTCATCCAACAACCACAGAATACACATTC" +
                "TATTCAACAGCACATGGAATTTTCTCCAAGATAGACCATATGATAGGCCATAAAATGAGTCTCAATAAATTTAAGAAAATTGAAATTGTATCACGCACTCTCT" +
                "CACATCACAATGGAATAAAACTGAAAATCAACTCCAAAAGGAATCTTCGAAACCATGCAAATACATGGAAATTAAATAACCTGCTCCTGAATGAGCATTGGGT" +
                "GAAAAACGAAATCAAGATGGAAATGTAAAAAATTTCTTCGAACTGGATGACACAACCTATCAAGACCTCTGGGATACAGCAAAGGCAGTGCTAAGAGGAAAGT" +
                "TTATAGCACTAAACACCTACGTCGAAAAGTCTGAAAGAGCACAGACAATCTAAGTTCACATCTCAGGGAACTAGAGAAGGAGGAACAAGCCAAACCCAATCCC" +
                "AGCAAACAAAGGAAATAACCAAGATCAGAGCAGAACTAAATGAAATTGACACAACAACAACAACAACAAAAATACAAAACATAAATAAAACAAAAATTTGGTT" +
                "ATTTGAAAAGATA");
        //String gene = mystery("AATGCTAACTAGCTGACTAAT");
        System.out.println(gene);


    }
//    String[] cars = {"Volvo", "BMW", "Ford", "Mazda"};
//    for (int i = 0; i < cars.length; i++) { // this is a regular for loop
//        System.out.println(cars[i]);
//    }


}
