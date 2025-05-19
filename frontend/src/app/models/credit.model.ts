export interface Credit {
  id?: number;
  montant: number;
  duree: number;
  tauxInteret: number;
  date: Date;
  etat: string;
  clientId: number;
}

export interface CreditImmobilier extends Credit {
  montantAchat: number;
  adresseBien: string;
  superficieBien: number;
}

export interface CreditPersonnel extends Credit {
  motif: string;
  revenuMensuel: number;
}

export interface CreditProfessionnel extends Credit {
  chiffreAffaire: number;
  secteurActivite: string;
  nomProjet: string;
} 