export interface TransferResponse {
    transferId: string;
    destinationAccount: string;
    originAccount: string;
    scheduledDate: string;
    transferDate: string;
    transferAmount: number;
    transferTax: number;
}