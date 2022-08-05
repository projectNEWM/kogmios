package io.newm.kogmios.protocols.localtxsubmit

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.createLocalTxSubmitClient
import io.newm.kogmios.protocols.messages.SubmitFail
import io.newm.kogmios.protocols.messages.SubmitSuccess
import kotlinx.coroutines.runBlocking
import org.junit.Ignore
import org.junit.jupiter.api.Test

class LocalTxSubmitTest {
    @Test
    fun `test SubmitTx old tx`() = runBlocking {
        val client = createLocalTxSubmitClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.submit(
            "83a400848258208883fa7cc2a7a1de8e1174541f29224b9086a57a4d413dca4833619ddc8f314e00825820f2262a007f13b638132817bd96ac5e598c5959d57e0038f5d1c8dfb0c967e5ef008258208883fa7cc2a7a1de8e1174541f29224b9086a57a4d413dca4833619ddc8f314e01825820ca90bb5463e0817e86bdfdcba8a96015a78ffeec0c0084813336d2b9650c29b709018482581d60e4995af54cc0e3ab8ccd0333840dfdcb7794c73e2eeba8e30906cb661ab2e133c082581d6016db1fb94af61989d8672b111203e6ba19bf354823f48aeb758244b81a2c8650c0825839000c1b05025ce45d2329e0e8c0382a2a3dabe4345079f725346e49762dfaddb4bf064802bdf863385e7d2c22c467c474880beb7b5e9dd4fb33821a0011b0dea1581c698a6ea0ca99f315034072af31eaac6ec11fe8558d3f48e9775aab9da14574445249501a3baa0c4082581d7047a9b7a5a73221ec30b9938b847bd323e46a4423985aabdd2a9a4fe6821a00101ccea1581c698a6ea0ca99f315034072af31eaac6ec11fe8558d3f48e9775aab9da14574445249501b000000a33df2ec17021a00030c35031a03ea8d18a2008582582035030cd6541d94d415ae59cbdf00bbdc1d4b1c6356bb135a8495d556d117fb8d584049919a07907e6e9dc62a796869159a21101ae7542c0f77bc2682d15d7e39364638fb19d1c857432038db730130c2948909fd5a2f15f7c59bec075e5d05e1ac058258201ccd62a659fe0665f55239503056252c19ff9b56000994e958f19214349996fd5840122bee6271ead4222fd22f7df0ff71b00f57b106930f8ce60714f9250522ad75977643c05fabdfb7355aa2bd68d68bead2f3be32192255748464090bf54c8d0b8258208a6b5c4b896781e20262a033c46436dcb8d396281d26620ca99fef00e060470358402943574009a587563ed4cf4b0e10aa4c6339034c768753b451a3ae1042f4e2555424852d22cb1d5b4cbdcf6be7fc9a9239cefff9bc323ceac68e146656564300825820f6c2c6b8acb78586c0ac0e0c1c84dbff594c73dae3b5d3f7fef0718dd50c414a5840d3241d0c4fa8aebe485a3c5a41febb39549a3888dd31bed2e35cff524f022f6bbc25b972068ce3da7651a1627cab0b6924231e3eec1a095dd18cd71660828f08825820081aebcf44f5be3075188e6121352f136fd0825ad0bd13705a8477bc5883699c5840a3b73c85788e6a65f3636929564fac8f3b79db6add2ae106759ff52f800a38fc3b4051f8998d45781c73d66637903a53eba9165d2ac8baecd94af80d62d37e0d01818201828200581ce4995af54cc0e3ab8ccd0333840dfdcb7794c73e2eeba8e30906cb668200581cc25b5e6bb88381521d184a7b440f8159dbf1fa257a0d50cc779d4debf6"
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(SubmitFail::class.java)
        println(response.result)
    }

    @Test
    @Ignore
    fun `test SubmitTx valid tx`() = runBlocking {
        val client = createLocalTxSubmitClient(websocketHost = "clockwork", websocketPort = 1337)
        val connectResult = client.connect()
        assertThat(connectResult).isTrue()
        assertThat(client.isConnected).isTrue()

        val response = client.submit(
            "84a4008282582065a390ff705fcf738c7c0fc490ced778f06814f268346618f1c8af2d757696470082582065a390ff705fcf738c7c0fc490ced778f06814f268346618f1c8af2d7576964701018282581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b1a000f424082581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b821b0001ad34bfdbcbcba1581c8d18d786e92776c824607fd8e193ec535c79dc61ea2405ddf3b09fe3a145707266697401021a0002beb1031a03ed1803a100818258204499320a77997987955eadba91721d5be54ca36536c5448009e822ba3f882d695840f36654ed91e88aeba1d5c60956c097621684b321b1605cea5f948d62c8055859ff54e4178ec08d720884d51221f291d7519a4914ad951bf0d356c387f239230bf5f6"
        )
        assertThat(response).isNotNull()
        assertThat(response.result).isInstanceOf(SubmitSuccess::class.java)
        println(response.result)
    }
}
