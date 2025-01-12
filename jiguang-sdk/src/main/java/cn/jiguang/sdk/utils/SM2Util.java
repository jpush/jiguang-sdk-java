package cn.jiguang.sdk.utils;

import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Curve;

import java.security.SecureRandom;
import java.security.Security;
import java.util.Base64;

public class SM2Util {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    private static final String BASE64_PUBLIC_KEY = "BPj6Mj/T444gxPaHc6CDCizMRp4pEl14WI2lvIbdEK2c+5XiSqmQt2TQc8hMMZqfxcDqUNQW95puAfQx1asv3rU=";

    private static final ECPublicKeyParameters PUBLIC_KEY = initializePublicKey();

    private static ECPublicKeyParameters initializePublicKey() {
        X9ECParameters sm2Params = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters ecDomainParameters = new ECDomainParameters(
                sm2Params.getCurve(),
                sm2Params.getG(),
                sm2Params.getN(),
                sm2Params.getH()
        );
        SM2P256V1Curve CURVE = new SM2P256V1Curve();
        ECPoint ecPoint = CURVE.decodePoint(Base64.getDecoder().decode(BASE64_PUBLIC_KEY));
        return new ECPublicKeyParameters(ecPoint, ecDomainParameters);
    }

    public static byte[] encrypt(String plainText) throws Exception {
        SM2Engine engine = new SM2Engine();
        engine.init(true, new ParametersWithRandom(PUBLIC_KEY, new SecureRandom()));
        byte[] plainBytes = plainText.getBytes();
        return engine.processBlock(plainBytes, 0, plainBytes.length);
    }

}
