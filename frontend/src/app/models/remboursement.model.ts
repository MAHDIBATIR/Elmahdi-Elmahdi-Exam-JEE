export interface Remboursement {
  id?: number;
  creditId: number;
  montant: number;
  dateEcheance: Date;
  regle: boolean;
  datePaiement?: Date;
} 