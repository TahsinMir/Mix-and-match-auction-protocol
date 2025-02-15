package PET;

import edu.boisestate.elgamal.ElGamal;
import edu.boisestate.elgamal.ElGamalMessage;
import edu.boisestate.elgamal.ElGamalPrivateKey;

import java.math.BigInteger;

public class CheckPET {

   public boolean checkEqualityOfTwoMessage(ElGamalMessage m1, ElGamalMessage m2, ElGamalPrivateKey privateKey){

       BigInteger alphaMessage1, betaMessage1,alphaMessage2, betaMessage2, divideAlpha, divideBeta, decryptmessage;

       // get alpha and beta for message 1
       alphaMessage1 =m1.getEncryptedMessage();
       betaMessage1 =m1.getEphimeralKey();

       // get alpha and beta for message 2
       alphaMessage2 = m2.getEncryptedMessage();
       betaMessage2 = m2.getEphimeralKey();
       BigInteger alphaMessage2Inverse = ElGamal.extendedEuclidAlgorithm(privateKey.getPublicKey().getP(), alphaMessage2).getT();
       BigInteger betaMessage2Inverse = ElGamal.extendedEuclidAlgorithm(privateKey.getPublicKey().getP(), betaMessage2).getT();

       divideAlpha = (alphaMessage1.multiply(alphaMessage2Inverse)).mod(privateKey.getPublicKey().getP());
       divideBeta = (betaMessage1.multiply(betaMessage2Inverse)).mod(privateKey.getPublicKey().getP());

       ElGamalMessage newelgamal=new ElGamalMessage(divideBeta, divideAlpha);
       decryptmessage=ElGamal.decryptMessage(newelgamal, privateKey);

       if (decryptmessage.equals(BigInteger.valueOf(1)))
       {
           return true;
       }
       return false;
    }
}
