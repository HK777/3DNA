package ru.dna.dna.utils;

public class File {

  public static final String PAIR = "pair";
  public static final String PAIR_PAR = "pairpar";
  public static final String STEP = "step";
  public static final String SIMPLEBP = "simplebp";
  public static final String SIMPLESTEP = "simplestep";
  public static final String BSTEP = "bstep";
  public static final String MAINTORSION = "maintorsion";
  public static final String PSEUDOTORSION = "pseudotorsion";
  public static final String SUGARTORSION = "sugartorsion";
  public static final String SUMMARY_WITHOUT_DNA = "_summary.txt";
  public static final String TORSION_WITHOUT_DNA = "_torsion.txt";

  public static String getSummary(String dna) {
    return dna + ".out";
  }

  public static String getTorsion(String dna) {
    return dna + ".tor";
  }

  public static String getCsvFile(String filename) {
    return filename + ".csv";
  }

  public static String getXmlFle(String filename) {
    return filename + ".xml";
  }
}
