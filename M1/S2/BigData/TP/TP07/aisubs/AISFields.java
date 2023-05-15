/**
 * Champs des données AIS
 * 
 * @author Frédéric Raimbault
 */
package aisubs;

/**
 * Liste ordonnées des (premiers) champs des signaux AIS
 */
public enum AISFields {

  /**
   * Maritime Mobile Service Identity (unique number identifying the ship station, 9 digits)
   */
  MMSI,
  /**
   * Data timestamp AIS format – unix timestamp Human readable format – UTC
   */
  TIME,
  /**
   * Geographical latitude  – in degrees
   */
  LATITUDE,
  /**
   * Geographical longitude – in degrees
   */
  LONGITUDE,
  /**
   * Course Over Ground -- in degrees. COG=360 means “not available”
   */
  COG,
  /**
   * Speed Over Ground -- in knots. SOG=1024 means “not available”
   */
  SOG,
  /**
   * Current heading of the AIS vessel at the time of the last message value in degrees, HEADING=511 means “not available”
   */
  HEADING,
  /**
   * Navigational Status {@link NAVIGATIONAL_STATUS}
   */
  NAVSTAT,
  /**
   * IMO ship identification number
   */
  IMO,
  /**
   * Vessel’s name (max.20 chars)
   */
  NAME,
  /**
   * Vessel’s callsign (radio station)
   */
  CALLSIGN,
  /**
   * Vessel’s type {@link VESSEL_TYPE}
   */
  TYPE;
  /** others fields are not considered (their content may vary) */
}

/**
 * Navigation STATUS
 */
enum NAVIGATIONAL_STATUS{
  /** 
   * Under way using engine
   */
  UNDERWAY,
  /** 
   * At anchor
   */
  AT_ANCHOR,
  /**
   * Not under command
   */
  NOT_UNDER_COMMAND,
  /**
   * Restricted manoeuverability
   */
  RESTRICTED_MANOEUVRABILITY,
  /** 
   * Constrained by her draught
   */
  CONSTRAINED_DRAUGHT,
  /**
   *  Moored
   */
  MOORED,
  /**
   * Aground
   */
  AGROUND,
  /** 
   * Engaged in Fishing
   */
  FISHING,
  /**
   * Under way sailing
   */
  SAILING,
  /**
   * Reserved for future amendment of Navigational Status for HSC
   */
  RESERVED_9,
  /**
   * Reserved for future amendment of Navigational Status for WIG
   */
  RESERVED_10,
  /**
   * Reserved for future use
   */
  RESERVED_11,
  /**
   * Reserved for future use
   */
  RESERVED_12,
  /**
   * Reserved for future use
   */
  RESERVED_13,
  /**
   * AIS-SART is active
   */
  AIS_SART,
  /**
   * Not defined (default)
   */
  NOT_DEFINED,
}

enum VESSEL_TYPE{
    /**
     * Not available (default)
     */
    NOT_AVAILABLE,
    /**
     * Reserved for future use
     */
    RESERVED_1,
    /**
     * Reserved for future use
     */
    RESERVED_2,
    /**
     * Reserved for future use
     */
    RESERVED_3,
    /**
     * Reserved for future use
     */
    RESERVED_4,
    /**
     * Reserved for future use
     */
    RESERVED_5,
    /**
     * Reserved for future use
     */
    RESERVED_6,
    /**
     * Reserved for future use
     */
    RESERVED_7,
    /**
     * Reserved for future use
     */
    RESERVED_8,
    /**
     * Reserved for future use
     */
    RESERVED_9,
    /**
     * Reserved for future use
     */
    RESERVED_10,
    /**
     * Reserved for future use
     */
    RESERVED_11,
    /**
     * Reserved for future use
     */
    RESERVED_12,
    /**
     * Reserved for future use
     */
    RESERVED_13,
    /**
     * Reserved for future use
     */
    RESERVED_14,
    /**
     * Reserved for future use
     */
    RESERVED_15,
    /**
     * Reserved for future use
     */
    RESERVED_16,
    /**
     * Reserved for future use
     */
    RESERVED_17,
    /**
     * Reserved for future use
     */
    RESERVED_18,
    /**
     * Reserved for future use
     */
    RESERVED_19,
    /**
     * Wing in ground (WIG), all ships of this type
     */
    WING_IN_GROUND_ALL_SHIPS,
    /**
     * Wing in ground (WIG), Hazardous category A
     */
    WING_IN_GROUND_A,
    /**
     * Wing in ground (WIG), Hazardous category B
     */
    WING_IN_GROUND_B,
    /**
     * Wing in ground (WIG), Hazardous category C
     */
    WING_IN_GROUND_C,
    /**
     * Wing in ground (WIG), Hazardous category D
     */
    WING_IN_GROUND_D,
    /**
     * Wing in ground (WIG), Reserved for future use
     */
    WING_IN_GROUND_25, 
    /**
     * Wing in ground (WIG), Reserved for future use
     */
    WING_IN_GROUND_26, 
    /**
     * Wing in ground (WIG), Reserved for future use
     */
    WING_IN_GROUND_27, 
    /**
     * Wing in ground (WIG), Reserved for future use
     */
    WING_IN_GROUND_28, 
    /**
     * Wing in ground (WIG), Reserved for future use
     */
    WING_IN_GROUND_29, 
    /**
     * Fishing
     */
    FISHING,
    /**
     * Towing
     */
    TOWING,
    /**
     * Towing: length exceeds 200m or breadth exceeds 25m
     */
    TOWING_BIG,
    /**
     * Dredging or underwater ops
     */
    DREDGING,
    /**
     * Diving ops
     */
    DIVING_OPS,
    /**
     * Military ops
     */
    MILITARY_OPS,
    /**
     * Sailing
     */
    SAILING,
    /**
     * Pleasure Craft
     */
    PLEASURE_CRAFT,
    /**
     * Reserved
     */
    RESERVED_38,
    /**
     * Reserved
     */
    RESERVED_39,
    /**
     * High speed craft (HSC), all ships of this type
     */
    HIGH_SPEED_CRAFT_ALL_SHIPS,
    /**
     * High speed craft (HSC), Hazardous category A
     */
    HIGH_SPEED_CRAFT_A,
    /**
     * High speed craft (HSC), Hazardous category B
     */
    HIGH_SPEED_CRAFT_B,
    /**
     * High speed craft (HSC), Hazardous category C
     */
    HIGH_SPEED_CRAFT_C,
    /**
     * High speed craft (HSC), Hazardous category D
     */
    HIGH_SPEED_CRAFT_D,
    /**
     * High speed craft (HSC), Reserved for future use
     */
    HIGH_SPEED_CRAFT_45,
    /**
     * High speed craft (HSC), Reserved for future use
     */
    HIGH_SPEED_CRAFT_46,
    /**
     * High speed craft (HSC), Reserved for future use
     */
    HIGH_SPEED_CRAFT_47,
    /**
     * High speed craft (HSC), Reserved for future use
     */
    HIGH_SPEED_CRAFT_48,
    /**
     * High speed craft (HSC), No additional information
     */
    HIGH_SPEED_CRAFT,
    /**
     * Pilot Vessel
     */
    PILOT_VESSEL,
    /**
     * Search and Rescue vessel
     */
    SEARCH_AND_RESCUE_VESSEL,
    /**
     * Tug
     */
    TUG,
    /**
     * Port Tender
     */
    PORT_TENDER,
    /**
     * Anti-pollution equipment
     */
    ANTI_POLLUTION_EQUIPMENT,
    /**
     * Law Enforcement
     */
    LAW_ENFORCEMENT,
    /**
     * Spare - Local Vessel
     */
    SPARE_LOCAL_VESSEL_56,
    /**
     * Spare - Local Vessel
     */
    SPARE_LOCAL_VESSEL_57,
    /**
     * Medical Transport
     */
    MEDICAL_TRANSPORT,
    /**
     * Noncombatant ship according to RR Resolution No. 18
     */
    NONCOMBATANT_SHIP,
    /**
     * Passenger, all ships of this type
     */
    PASSENGER_ALL_SHIPS,
    /**
     * Passenger, Hazardous category A
     */
    PASSENGER_A,
    /**
     * Passenger, Hazardous category B
     */
    PASSENGER_B,
    /**
     * Passenger, Hazardous category C
     */
    PASSENGER_C,
    /**
     * Passenger, Hazardous category D
     */
    PASSENGER_D,
    /**
     * Passenger, Reserved for future use
     */
    PASSENGER_65,
    /**
     * Passenger, Reserved for future use
     */
    PASSENGER_66,
    /**
     * Passenger, Reserved for future use
     */
    PASSENGER_67,
    /**
     * Passenger, Reserved for future use
     */
    PASSENGER_68,
    /**
     * Passenger, No additional information
     */
    PASSENGER,
    /**
     * Cargo, all ships of this type
     */
    CARGO_ALL_SHIPS,
    /**
     * Cargo, Hazardous category A
     */
    CARGO_A,
    /**
     * Cargo, Hazardous category B
     */
    CARGO_B,
    /**
     * Cargo, Hazardous category C
     */
    CARGO_C,
    /**
     * Cargo, Hazardous category D
     */
    CARGO_D,
    /**
     * Cargo, Reserved for future use
     */
    CARGO_75,
    /**
     * Cargo, Reserved for future use
     */
    CARGO_76,
    /**
     * Cargo, Reserved for future use
     */
    CARGO_77,
    /**
     * Cargo, Reserved for future use
     */
    CARGO_78,
    /**
     * Cargo, No additional information
     */
    CARGO,
    /**
     * Tanker, all ships of this type
     */
    TANKER_ALL_SHIPS,
    /**
     * Tanker, Hazardous category A
     */
    TANKER_A,
    /**
     * Tanker, Hazardous category B
     */
    TANKER_B,
    /**
     * Tanker, Hazardous category C
     */
    TANKER_C,
    /**
     * Tanker, Hazardous category D
     */
    TANKER_D,
    /**
     * Tanker, Reserved for future use
     */
    TANKER_85,
    /**
     * Tanker, Reserved for future use
     */
    TANKER_86,
    /**
     * Tanker, Reserved for future use
     */
    TANKER_87,
    /**
     * Tanker, Reserved for future use
     */
    TANKER_88,
    /**
     * Tanker, No additional information
     */
    TANKER,
    /**
     * Other Type, all ships of this type
     */
    OTHER_TYPE_ALL_SHIPS,
    /**
     * Other Type, Hazardous category A
     */
    OTHER_TYPE_A,
    /**
     * Other Type, Hazardous category B
     */
    OTHER_TYPE_B,
    /**
     * Other Type, Hazardous category C
     */
    OTHER_TYPE_C,
    /**
     * Other Type, Hazardous category D
     */
    OTHER_TYPE_D,
    /**
     * Other Type, Reserved for future use
     */
    OTHER_TYPE_95,
    /**
     * Other Type, Reserved for future use
     */
    OTHER_TYPE_96,
    /**
     * Other Type, Reserved for future use
     */
    OTHER_TYPE_97,
    /**
     * Other Type, Reserved for future use
     */
    OTHER_TYPE_98,
    /**
     * Other Type, no additional information
     */
    OTHER_TYPE_99,
    }
