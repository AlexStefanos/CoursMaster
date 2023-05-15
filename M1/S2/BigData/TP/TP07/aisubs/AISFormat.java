/**
 * Classe contenant des utilitaires pour le traitement des données AIS
 * 
 * @author Frédéric Raimbault
 */
package aisubs;

import java.time.Instant;
import java.util.EnumMap;
import java.util.Scanner;

/**
 * Fonctions d'extraction et de formatage des signaux AIS
 */
public class AISFormat {

  /**
   * Création d'une table contenant la valeur des principaux signaux AIS à partir d'une ligne de texte 
   * @param line la ligne de texte contenant les valeurs ORDONNEES des champs d'un signal AIS
   * @return une table (nom du champ -> valeur du champ) ; la valeur vaut null si absente
   */
  public static EnumMap<AISFields, String> getFieldMap(String line) {

    EnumMap<AISFields, String> fieldMap= new EnumMap<>(AISFields.class);
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(line).useDelimiter(",");
    for (AISFields key:AISFields.values()) {
      String value= scanner.hasNext() ? scanner.next() : null;
      fieldMap.put(key, value);
    }
    scanner.close();
    return fieldMap;
  }

  /**
   * Création d'un tuple pour MapReduce contenant la valeur des principaux signaux AIS à partir d'une ligne de text
   */
  public static TupleWritable getFieldTuple(String line) {
    return TupleWritable.valueOf(line, ",");
  }
  
  /**
   * Conversion d'une date au format ISO en une date au format Unix 
   * @param isotime une chaîne au format \"2020-09-14 23:59:44 GMT\"
   * @return le nombre de secondes depuis 1970 de cette date (-1 si la conversion échoue)
   */
  public static long isoTimeToEpoch(String isotime) {
    
    Instant instant;
    try {
      String date= isotime.substring(1,11);
      String time= isotime.substring(12,20);
      instant = Instant.parse(date+"T"+time+"Z");
    } catch (Exception e) {
      return -1;
    }
    return instant.getEpochSecond();
  }
  
  /**
   * Arrondi d'une date à la minute
   * @param epoch date sous la forme d'un nombre de secondes depuis 1970
   * @return la même date tronquée à la minute
   */
  public static long trunkToMinute(long epoch) {
    return epoch-epoch%60;
  }
  
  /**
   * Ecriture ORDONNEE de l'ensemble des valeurs des champs dans une chaîne  
   * @param fields la table des valeurs des champs
   * @return la chaîne au format CSV
   */
  public static String mapToString(EnumMap<AISFields, String> fields) {
    StringBuffer sb= new StringBuffer();
    for (AISFields key:fields.keySet()) {
      String value= fields.get(key);
      sb.append(value != null ? value+"," : ",");
    }
    return sb.toString();
  }
  
  /**
   * Test si MMSI appartenant à un navire
   * @param mmsi chaîne contenant un MMSI
   * @return vrai ssi le mmsi est celui d'un navire
   */
  public static boolean isShipMMSI(String mmsi) {
    char first= mmsi.charAt(0);
    return (first >= '1') && (first <'8');
  }
  /**
   * Tests
   * @param args
   */
  public static void main(String[] args) {
    
    String line1= "372771000,\"2020-09-14 23:59:40 GMT\",57.2601,11.54937,158.7,8.4,159,0,9808340,\"CIELO DI IYO\",\"HOET\",70,160,23,19,11,7.4,\"RONNSKAR\",\"09-18 08:00\"";
    System.out.println("line1= "+line1);
    EnumMap<AISFields, String> fields= AISFormat.getFieldMap(line1);
    for (AISFields key:fields.keySet()) {
      System.out.println(key+": "+fields.get(key));
    }
    String time= fields.get(AISFields.TIME);
    long epoch= isoTimeToEpoch(time);
    System.out.println("isoTimeToUnix("+time+")= "+epoch);
    System.out.println("round to minute= "+Instant.ofEpochSecond(trunkToMinute(epoch)));
    epoch= AISFormat.trunkToMinute(epoch);
    fields.put(AISFields.TIME, Instant.ofEpochSecond(epoch).toString());
    System.out.println("line 2= "+AISFormat.mapToString(fields));
  }
  
}
