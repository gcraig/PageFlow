package com.itsy.sparky.pageflow.events;

public enum Event {

	/* FIXME: Autogenerate this boilerplate, through JSP code generator */

	OkContinue,
	Stop,
	Welcome,
	FirstTimeDonor,
	PreviousDonor,
	SaveDonorAddress,
	SexEntry,
	IdEntry,
	BirthDateEntry,
	DonorNotFound,
	DonorConfirmation,
	NotMe,
	KioskRegistered,
	DisplayDemographics,
	ChangeDemographics,
	ChangeDemographicItem,
	ContactPreference,
	RaceSelection,
	SpecialEvents,
	BirthDateCheck,
	MilestoneCheck,
	DisplayContactPreference,
	DisplayRaceSelection,
	ProcessRaceSelection,
	DisplayFinalMessage;

	public String value() {

		switch (this) {
		
		case OkContinue:
			return "OkContinue";
			
		case Stop:
			return "Stop";
			
		case Welcome:
			return "Welcome";
			
		case FirstTimeDonor:
			return "FirstTimeDonor";
			
		case PreviousDonor:
			return "PreviousDonor";
			
		case SaveDonorAddress:
			return "SaveDonorAddress";
			
		case SexEntry:
			return "SexEntry";
			
		case IdEntry:
			return "IdEntry";
			
		case BirthDateEntry:
			return "BirthDateEntry";
			
		case DonorNotFound:
			return "DonorNotFound";
			
		case DonorConfirmation:
			return "DonorConfirmation";
			
		case NotMe:
			return "NotMe";
			
		case KioskRegistered:
			return "KioskRegistered";
			
		case DisplayDemographics:
			return "DisplayDemographics";
			
		case ChangeDemographics:
			return "ChangeDemographics";
			
		case ChangeDemographicItem:
			return "ChangeDemographicItem";
			
		case DisplayContactPreference:
			return "DisplayContactPreference";
			
		case ContactPreference:
			return "ContactPreference";
			
		case RaceSelection:
			return "RaceSelection";
			
		case SpecialEvents:
			return "SpecialEvents";
			
		case BirthDateCheck:
			return "BirthDateCheck";
			
		case MilestoneCheck:
			return "MilestoneCheck";

		case DisplayRaceSelection:
			return "DisplayRaceSelection";

		case ProcessRaceSelection:
			return "ProcessRaceSelection";

		case DisplayFinalMessage:
			return "DisplayFinalMessage";
			
		default:
			return "OkContinue";
		}
	}
}
