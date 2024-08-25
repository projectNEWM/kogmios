package io.newm.kogmios.protocols.txsubmit

import com.google.common.truth.Truth.assertThat
import io.newm.kogmios.createTxSubmitClient
import io.newm.kogmios.exception.KogmiosException
import io.newm.kogmios.protocols.model.fault.CannotCreateEvaluationContextFaultData
import io.newm.kogmios.protocols.model.result.SubmitTxResult
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class TxSubmitTest {
    companion object {
        // local testing
        private const val TEST_HOST = "localhost"
        private const val TEST_PORT = 1337
        private const val TEST_SECURE = false

        // remote testing
//        private const val TEST_HOST = "ogmios-kogmios-9ab819.us1.demeter.run"
//        private const val TEST_PORT = 443
//        private const val TEST_SECURE = true
    }

    @Test
    fun `test SubmitTx old tx`() =
        runBlocking {
            createTxSubmitClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val exception =
                    assertThrows<KogmiosException> {
                        client.submit(
                            "83a400848258208883fa7cc2a7a1de8e1174541f29224b9086a57a4d413dca4833619ddc8f314e00825820f2262a007f13b638132817bd96ac5e598c5959d57e0038f5d1c8dfb0c967e5ef008258208883fa7cc2a7a1de8e1174541f29224b9086a57a4d413dca4833619ddc8f314e01825820ca90bb5463e0817e86bdfdcba8a96015a78ffeec0c0084813336d2b9650c29b709018482581d60e4995af54cc0e3ab8ccd0333840dfdcb7794c73e2eeba8e30906cb661ab2e133c082581d6016db1fb94af61989d8672b111203e6ba19bf354823f48aeb758244b81a2c8650c0825839000c1b05025ce45d2329e0e8c0382a2a3dabe4345079f725346e49762dfaddb4bf064802bdf863385e7d2c22c467c474880beb7b5e9dd4fb33821a0011b0dea1581c698a6ea0ca99f315034072af31eaac6ec11fe8558d3f48e9775aab9da14574445249501a3baa0c4082581d7047a9b7a5a73221ec30b9938b847bd323e46a4423985aabdd2a9a4fe6821a00101ccea1581c698a6ea0ca99f315034072af31eaac6ec11fe8558d3f48e9775aab9da14574445249501b000000a33df2ec17021a00030c35031a03ea8d18a2008582582035030cd6541d94d415ae59cbdf00bbdc1d4b1c6356bb135a8495d556d117fb8d584049919a07907e6e9dc62a796869159a21101ae7542c0f77bc2682d15d7e39364638fb19d1c857432038db730130c2948909fd5a2f15f7c59bec075e5d05e1ac058258201ccd62a659fe0665f55239503056252c19ff9b56000994e958f19214349996fd5840122bee6271ead4222fd22f7df0ff71b00f57b106930f8ce60714f9250522ad75977643c05fabdfb7355aa2bd68d68bead2f3be32192255748464090bf54c8d0b8258208a6b5c4b896781e20262a033c46436dcb8d396281d26620ca99fef00e060470358402943574009a587563ed4cf4b0e10aa4c6339034c768753b451a3ae1042f4e2555424852d22cb1d5b4cbdcf6be7fc9a9239cefff9bc323ceac68e146656564300825820f6c2c6b8acb78586c0ac0e0c1c84dbff594c73dae3b5d3f7fef0718dd50c414a5840d3241d0c4fa8aebe485a3c5a41febb39549a3888dd31bed2e35cff524f022f6bbc25b972068ce3da7651a1627cab0b6924231e3eec1a095dd18cd71660828f08825820081aebcf44f5be3075188e6121352f136fd0825ad0bd13705a8477bc5883699c5840a3b73c85788e6a65f3636929564fac8f3b79db6add2ae106759ff52f800a38fc3b4051f8998d45781c73d66637903a53eba9165d2ac8baecd94af80d62d37e0d01818201828200581ce4995af54cc0e3ab8ccd0333840dfdcb7794c73e2eeba8e30906cb668200581cc25b5e6bb88381521d184a7b440f8159dbf1fa257a0d50cc779d4debf6",
                        )
                    }
                assertThat(exception).isInstanceOf(KogmiosException::class.java)
                assertThat(
                    exception.message
                ).isEqualTo(
                    "The transaction contains unknown UTxO references as inputs. This can happen if the inputs you're trying to spend have already been spent, or if you've simply referred to non-existing UTxO altogether. The field 'data.unknownOutputReferences' indicates all unknown inputs."
                )
                assertThat(exception.jsonRpcErrorResponse).isNotNull()
                assertThat(exception.jsonRpcErrorResponse.error.code).isEqualTo(3117L)
            }
        }

    @Test
    @Disabled
    fun `test SubmitTx valid tx`() =
        runBlocking {
            createTxSubmitClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.submit(
                        "84a400828258208505c781988915ee496419bb7878680ead25390b14b3f38ce6c943d14cced82a008258208505c781988915ee496419bb7878680ead25390b14b3f38ce6c943d14cced82a01018282581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b1a000f424082581d60da0eb5ed7611482ec5089b69d870e0c56c1c45180256112398e0835b1b00000221963d86df021a0002b1a1031a02fa0065a100818258204499320a77997987955eadba91721d5be54ca36536c5448009e822ba3f882d695840126d5a28fe558103293f607677cdc562343c5b0e16ebd49806afc4a2c173f11d6f7d82f239c175098407a0d1dc207ceafa7a3bc731c05346012094aae094bd0bf5f6",
                    )
                assertThat(response).isNotNull()
                assertThat(response.result).isInstanceOf(SubmitTxResult::class.java)
                assertThat(response.result.transaction.id).isEqualTo("b831f224dfcbf5dd0e0012a491d84579de436d2d4a1a251c88001c83cad20474")
                println(response.result)
            }
        }

    @Test
    @Disabled
    fun `test EvaluateTx valid tx`() =
        runBlocking {
            createTxSubmitClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val response =
                    client.evaluate(
                        // working
//                        "84aa0081825820292f43ca3da4026c7b7d70267adb3d20e494fdb65751dcddddfc1252c4fb995c000d81825820f748cf7fa087e4f7252789e6c4954903d37eff007578b937b65e7603c26071bb0112828258207641e0d3e0abdea6ea4f5f8c9aefd3194d0f907207f6c8a119c0ac4656b0a23f00825820948046a408a3c35d8b482cbe622e9ac632b49f49280d680b114cdbf3562a0f71010182a300581d700e70594d46a226aa08a3351b1e71ca44e51ef5cd5e6ada385831badd01821a0014524ea1581ca95dbc93ec0bf7b3ecec07742372fb2bd0084acdf9615e96c5ce21f7a15820ca11ab1e00305f4f71a0ca0c680c4f8ae17222abfefff7c5b5b2ece1b1deb64001028201d818581cd8799f1879d8799f1b0000018d48374c001b0000018d61f71800ffff82581d60a91113cbeb8c42e56a66ebdb492be53909aaab740387e37cdf1c6b7f1a0119799f1082581d60a91113cbeb8c42e56a66ebdb492be53909aaab740387e37cdf1c6b7f1a004739a3111a0005119d021a000361130e83581c3ae131d72082b2be2b7f7eae7b1c54eb914a5679e87aec20983079a6581c77f07a29d7f2efd2f5d697886620e2c409f110df8d9f2bedadf898d9581ca91113cbeb8c42e56a66ebdb492be53909aaab740387e37cdf1c6b7f09a1581ca95dbc93ec0bf7b3ecec07742372fb2bd0084acdf9615e96c5ce21f7a15820ca11ab1e00305f4f71a0ca0c680c4f8ae17222abfefff7c5b5b2ece1b1deb640010b582027da63c9cc30e891bc5a90a2da58f6c4030de282ed06bf3547ee01a8f22a072ea20083825820a7f12998639de2ad89851710b0c4f3307b774056d851cf6ba1698ee27574a7905840ca4a742e40719db255bb34114cd5694a0a0f1b4d368bff65fdb87f340951a5411944aef032e775affb46afe34ec93fa5709e0b1bc464ee1d5df72299445a2a0f8258204d991397c2751adca62eb18a8b3de3959314135e6442dc89f346bd40aa32e89958406f47d2b81bcd4fdb5a8c3d1a7e1e3a12d6a57677d54d66b091da66f650aa1493ec8f03e24113b92952fb7c905dccf2f11fb9fe2aa5a5980f25dbd2877f6a0d098258201917d87ffec739f20d586a128a6d787198c5da11b44015a71c62c88fa628f8f258404717c2e533ceba6980244aa700ea38ea9fc27f0b245535348b243fd7a9b331a9f062d65bd0c0b2ab7bb7478596cdcc2fc0163a072c48601043bc0f99942d250e0581840100d87980821a00028b4d1a03ada6b2f5f6",
                        // broken
                        "84aa008182582034d18219c730a6984d80f24b9221517d2fecf08dec0376b9bad55174098e039c000182a300581d700e70594d46a226aa08a3351b1e71ca44e51ef5cd5e6ada385831badd01821a0013bac8a1581ca95dbc93ec0bf7b3ecec07742372fb2bd0084acdf9615e96c5ce21f7a15820ca11ab1e00305f4f71a0ca0c680c4f8ae17222abfefff7c5b5b2ece1b1deb64001028201d818581ad87982187bd879821b0000018d7bb6e4001b0000018d9576b000a200581d60e32a130a38872f9de02a598386da90ae91520918ce3c59a9dac83f96011a0396af98021a002dc6c009a1581ca95dbc93ec0bf7b3ecec07742372fb2bd0084acdf9615e96c5ce21f7a15820ca11ab1e00305f4f71a0ca0c680c4f8ae17222abfefff7c5b5b2ece1b1deb640010b5820c8e18324ccf9abfedcde1de2923366c6b09cfb37dc74e4e6b5448f35c670b5680d81825820f748cf7fa087e4f7252789e6c4954903d37eff007578b937b65e7603c26071bb010e81581c3ae131d72082b2be2b7f7eae7b1c54eb914a5679e87aec20983079a610a200581d6077f07a29d7f2efd2f5d697886620e2c409f110df8d9f2bedadf898d9011a000f4240111a003d090012828258207641e0d3e0abdea6ea4f5f8c9aefd3194d0f907207f6c8a119c0ac4656b0a23f00825820948046a408a3c35d8b482cbe622e9ac632b49f49280d680b114cdbf3562a0f7101a20083825820a7f12998639de2ad89851710b0c4f3307b774056d851cf6ba1698ee27574a7905840d3f6c099bd6e2ce0da571f2fb0fd84be31ea79e933b2718816856ef2a8bfc3eb63a162ae1074fd33ac281a5406e1d7106dc684601181d33f6fcecb1fb50499088258204d1ff441ae2bad05f39a8a2419beb89ad70dea555c17d0d5ba11a9e1dab6a0a15840200591ab8abc802418457782f3a2da4010d35d074eb74e2674dca91ec019fb6ee9515651dff1e932d47c0778662d101216d71b2a72df69a73f5ed3e1c14beb098258204d991397c2751adca62eb18a8b3de3959314135e6442dc89f346bd40aa32e8995840dcf418d7425545e8ba9660671289bd03f40874a0fdf2c6f4f62278c175582c6bfed3b2330092dc2a1d66cb00d1caf4f0b64a05de1da554262dc2f8b8f52dea060581840100d87980821a00d59f801b00000002540be400f5f6",
                    )
                assertThat(response).isNotNull()
                println(response.result)
            }
            // EvaluationResult(["spend:0": ExecutionUnits(memory=2857226, steps=780122210), "mint:0": ExecutionUnits(memory=4876337, steps=1311998920)])
            // This is a spend + mint transaction running 2 contracts
            // {
            //    "type": "Witnessed Tx BabbageEra",
            //    "description": "Ledger Cddl Format",
            //    "cborHex": "84ab0082825820859adaaeebd3c4b2d842a2a1eef62c6d7bff7ef199c9521370b7cda3587adc0300825820859adaaeebd3c4b2d842a2a1eef62c6d7bff7ef199c9521370b7cda3587adc03010d81825820949d496d900345508ed6e0b27b2a79a284fd3c37b7a3b7b01cece027fe1577aa0212828258200b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829018258200b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829020184a300581d7087692773cf94c4542105946daebe7afeb9100efb7a108158a2f9749c011a0018fda8028201d8185866d8799f581c602a7e54f9c569c770ba6f732af3077d8b06e14b84abc8d0151635d8581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55464e45574d5f30581c9bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8040ffa200581d609bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8001821a000fc8a0a1581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55a1464e45574d5f3001a300581d704347ac2bcda3f6516082cba79661714b836e68eba49d99429d73eec801821a00152d2ca1581cfd3a69817fe5b9ff39fb2fac2be2c7f2007746e827ee31868fe667cda1454e45574d5f01028201d8185829d8799f581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a5501454e45574d5fffa200581d608082e2be035d27cf02455606df19308b5f7022490f8a61f22e158146011a0c1498d710a200581d608082e2be035d27cf02455606df19308b5f7022490f8a61f22e158146011a003877eb111a0013d355021a000d378e0e83581c2eb8686d882a0eddc9c7f68ece8f198f973ce90477b51ca017f2a25d581c4c1017ed05f703f6cf31c6a743f2c69534dbe4846e0d51ed4cb24b85581c8082e2be035d27cf02455606df19308b5f7022490f8a61f22e15814609a1581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55a1464e45574d5f30010b582090fc0705a1a850814b9b3a8c86db1ff3e215bc99160f5af34a016d11e8f2fd2607582033021058cf5994f8993aaf9884c554c15500d2f7bdca95d28be8dd4244e7331ea2008382582056ca6b314617c38c921a05885f055e9b470aa793dd5d0721bee45d7192a6f0f55840b81356ecb164fd428967937a8ca21683a940958e9fabba589f90ce0c7deebd6c695571605ab59d3d32ac2337ab14d5185bb62732111eccc6754cf6d132819f0e825820161dfc32da5b2988f1a7f31737fddda7aed8d17c0935314a671c48034973d7fe58409b9637cc0a8a9edb27050586f92f50e4272f6402f39072ceca67942bb15d3ed2eaeecff5dc23ed8c4ac6458ac167be6e41152ccf59df3d69b5b1609913957206825820fc993172aa4ff02f2dec9985a6a359fb69a0ce80c85a06b3b195e54e9d0e18ae5840f411a24e5a29892b8d0c03a300e9554aee54a666e866075b3e7cf8cb148c32acb96a79ea6cfe32536f28c2bf78c4f4bef2c7a1f6939cf5f926810535b102b60c0582840000d87980821a002b990a1a2e7fb862840100d8799f581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a5500454e45574d5fff821a004a68311a4e3383c8f5d90103a100a11902d1a278383361623235633835336630663138386634336233346232646635636239383733376134646537653139303238656339643161343134613535a1664e45574d5f30b8186b616c62756d5f7469746c656d42696767657220447265616d73676172746973747381a1646e616d65644d55525369636f70797269676874827818c2a92032303232204d6f6f647377696e677a204d757a696b7829e284972032303232204d6f6f647377696e677a2043727970746f6d656469612047726f7570204c4c4371636f756e7472795f6f665f6f726967696e6d556e69746564205374617465736b6469737472696275746f726f68747470733a2f2f6e65776d2e696f686578706c696369746566616c73656566696c657382a3696d65646961547970656a617564696f2f666c6163646e616d656d42696767657220447265616d7363737263783061723a2f2f503134316f305244416a53596c565167544467484e414f5251546b4d5949564370726d445f644b4d567373a3696d65646961547970656f6170706c69636174696f6e2f706466646e616d65782153747265616d696e6720526f79616c74792053686172652041677265656d656e7463737263783061723a2f2f545779467778654f4978534c77707963556853686e677638687069616c41364445515045484a586244706f6667656e726573826748697020486f706352617065696d616765783061723a2f2f4375504659324c6e37795555684a583039473533306b645066393365476841566c686a727452374a68357764697372636f515a2d4e57372d32322d3230303435656c696e6b73a66654696b546f6b782768747470733a2f2f7777772e74696b746f6b2e636f6d2f406d6f6f647377696e677a6d757a696b6e6172746973745f74776974746572781868747470733a2f2f747769747465722e636f6d2f4d5552536866616365626f6f6b782e68747470733a2f2f7777772e66616365626f6f6b2e636f6d2f6d6f6f647377696e677a63727970746f6d6564696169696e7374616772616d782968747470733a2f2f7777772e696e7374616772616d2e636f6d2f6d6f6f647377696e677a6d757a696b6774776974746572782368747470733a2f2f747769747465722e636f6d2f6d6f6f647377696e677a6d757a696b6777656273697465781a687474703a2f2f4d6f6f647377696e677a4d757a696b2e636f6d696c797269636973747381644d555253726d6173746572696e675f656e67696e656572654162797373696d65646961547970656a696d6167652f776562706c6d69785f656e67696e656572654162797373646d6f6f64694665656c20476f6f64766d757369635f6d657461646174615f76657273696f6e01646e616d65744d555253202d2042696767657220447265616d736870726f64756365726541627973736c72656c656173655f747970656653696e676c656d736f6e675f6475726174696f6e665054344d30536a736f6e675f7469746c656d42696767657220447265616d736c747261636b5f6e756d626572016d76697375616c5f6172746973746541627973736776657273696f6e01"
            // }
            // This is the spend + mint transaction running 2 contracts for fractionalization
            // {
            //    "type": "Witnessed Tx BabbageEra",
            //    "description": "Ledger Cddl Format",
            //    "cborHex": "84ab008382582035fc6dbcd0bfc2a48949db64e62fa7fc74b916a40b546d7b65bfc830e80035d70082582035fc6dbcd0bfc2a48949db64e62fa7fc74b916a40b546d7b65bfc830e80035d701825820949d496d900345508ed6e0b27b2a79a284fd3c37b7a3b7b01cece027fe1577aa010d81825820949d496d900345508ed6e0b27b2a79a284fd3c37b7a3b7b01cece027fe1577aa021282825820892e397590da7601e4ccbe113d4d01017083f60cbb2d651f5fb722cee73b0b1201825820892e397590da7601e4ccbe113d4d01017083f60cbb2d651f5fb722cee73b0b12020183a200581d609bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8001821a00100bf8a1581c602a7e54f9c569c770ba6f732af3077d8b06e14b84abc8d0151635d8a1464e45574d5f301a05f5e100a300581d7087692773cf94c4542105946daebe7afeb9100efb7a108158a2f9749c01821a0018fda8a1581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55a1464e45574d5f3001028201d8185866d8799f581c602a7e54f9c569c770ba6f732af3077d8b06e14b84abc8d0151635d8581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55464e45574d5f30581c9bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8040ffa200581d609bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d80011a11d6de0d10a200581d609bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d80011a003c88d7111a000fc269021a000a819b0e82581c2eb8686d882a0eddc9c7f68ece8f198f973ce90477b51ca017f2a25d581c4c1017ed05f703f6cf31c6a743f2c69534dbe4846e0d51ed4cb24b8509a1581c602a7e54f9c569c770ba6f732af3077d8b06e14b84abc8d0151635d8a1464e45574d5f301a05f5e1000b582003eedcb95f6cd5f18955f2e14cfada1385446e332476d00f24e2662373145ea907582057a48f6d612c79e1a5639b6e04f2e59bd9458ba69d5775614598bf592dfd3cc4a2008382582056ca6b314617c38c921a05885f055e9b470aa793dd5d0721bee45d7192a6f0f558405eebffeff6989916813fb848b9b0eb719664e28e68277235e2da00b03a40982543c9dcb41a8ee956f374f6d49c3f5471015b1b09ab00dea427657e9ce248d108825820161dfc32da5b2988f1a7f31737fddda7aed8d17c0935314a671c48034973d7fe584074d3f4212b15ceed9625f4a556f92409f4a5f985f89196537c3e4be4eee51850150be075456139d7ff21a46be5ea4858ba0eed77723a3081ff5659522e2c3f0e8258203809753ed8904607fc12acf1cbdb1a209cee6d194baa16fe4d834553b63ce7ad58409822bbd49a88c8b988d858b7eff2aba2be5c3d0d0bb2b12fe76444d5db810d931f347b286921651f93422ec29fd712e874e72354e40ca3e35c3f176907e9e40e0582840000d87980821a00297f521a2caa4bad840100d8799f581c602a7e54f9c569c770ba6f732af3077d8b06e14b84abc8d0151635d8581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55464e45574d5f30581c9bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8040ff821a0028f5f91a2cb19cb8f5d90103a100a11902d1a278383630326137653534663963353639633737306261366637333261663330373764386230366531346238346162633864303135313633356438a1664e45574d5f30b8186b616c62756d5f7469746c656d42696767657220447265616d73676172746973747381a1646e616d65644d55525369636f70797269676874827818c2a92032303232204d6f6f647377696e677a204d757a696b7829e284972032303232204d6f6f647377696e677a2043727970746f6d656469612047726f7570204c4c4371636f756e7472795f6f665f6f726967696e6d556e69746564205374617465736b6469737472696275746f726f68747470733a2f2f6e65776d2e696f686578706c696369746566616c73656566696c657382a3696d65646961547970656a617564696f2f666c6163646e616d656d42696767657220447265616d7363737263783061723a2f2f503134316f305244416a53596c565167544467484e414f5251546b4d5949564370726d445f644b4d567373a3696d65646961547970656f6170706c69636174696f6e2f706466646e616d65782153747265616d696e6720526f79616c74792053686172652041677265656d656e7463737263783061723a2f2f545779467778654f4978534c77707963556853686e677638687069616c41364445515045484a586244706f6667656e726573826748697020486f706352617065696d616765783061723a2f2f4375504659324c6e37795555684a583039473533306b645066393365476841566c686a727452374a68357764697372636f515a2d4e57372d32322d3230303435656c696e6b73a66654696b546f6b782768747470733a2f2f7777772e74696b746f6b2e636f6d2f406d6f6f647377696e677a6d757a696b6e6172746973745f74776974746572781868747470733a2f2f747769747465722e636f6d2f4d5552536866616365626f6f6b782e68747470733a2f2f7777772e66616365626f6f6b2e636f6d2f6d6f6f647377696e677a63727970746f6d6564696169696e7374616772616d782968747470733a2f2f7777772e696e7374616772616d2e636f6d2f6d6f6f647377696e677a6d757a696b6774776974746572782368747470733a2f2f747769747465722e636f6d2f6d6f6f647377696e677a6d757a696b6777656273697465781a687474703a2f2f4d6f6f647377696e677a4d757a696b2e636f6d696c797269636973747381644d555253726d6173746572696e675f656e67696e656572654162797373696d65646961547970656a696d6167652f776562706c6d69785f656e67696e656572654162797373646d6f6f64694665656c20476f6f64766d757369635f6d657461646174615f76657273696f6e01646e616d65744d555253202d2042696767657220447265616d736870726f64756365726541627973736c72656c656173655f747970656653696e676c656d736f6e675f6475726174696f6e665054344d30536a736f6e675f7469746c656d42696767657220447265616d736c747261636b5f6e756d626572016d76697375616c5f6172746973746541627973736776657273696f6e01"
            // }
        }

    @Test
    fun `test EvaluateTx old tx`() =
        runBlocking {
            createTxSubmitClient(
                websocketHost = TEST_HOST,
                websocketPort = TEST_PORT,
                secure = TEST_SECURE,
            ).use { client ->
                val connectResult = client.connect()
                assertThat(connectResult).isTrue()
                assertThat(client.isConnected).isTrue()

                val exception =
                    assertThrows<KogmiosException> {
                        client.evaluate(
                            "84ab0082825820334958f7971903143be0672aa1818a1d96babe6cddbf445405ba488c8f70d10c02825820334958f7971903143be0672aa1818a1d96babe6cddbf445405ba488c8f70d10c030d81825820949d496d900345508ed6e0b27b2a79a284fd3c37b7a3b7b01cece027fe1577aa0212828258200b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829018258200b4c56fdae7c23748c837e2443dfebebd020239c107aed9b227851ebabe4a829020184a300581d7087692773cf94c4542105946daebe7afeb9100efb7a108158a2f9749c011a00190e7e028201d8185867d8799f581c602a7e54f9c569c770ba6f732af3077d8b06e14b84abc8d0151635d8581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55474e45574d5f3135581c9bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8040ffa200581d609bb822cdf2c6c79657c0e8ec24308487dbd35750c084e271620d9d8001821a000fd976a1581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55a1474e45574d5f313501a300581d704347ac2bcda3f6516082cba79661714b836e68eba49d99429d73eec801821a00152d2ca1581cfd3a69817fe5b9ff39fb2fac2be2c7f2007746e827ee31868fe667cda1454e45574d5f01028201d8185829d8799f581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a5510454e45574d5fffa200581d608082e2be035d27cf02455606df19308b5f7022490f8a61f22e158146011a08e8177d10a200581d608082e2be035d27cf02455606df19308b5f7022490f8a61f22e158146011a00381a37111a00143109021a000d76060e83581c2eb8686d882a0eddc9c7f68ece8f198f973ce90477b51ca017f2a25d581c4c1017ed05f703f6cf31c6a743f2c69534dbe4846e0d51ed4cb24b85581c8082e2be035d27cf02455606df19308b5f7022490f8a61f22e15814609a1581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a55a1474e45574d5f3135010b58202077f5fa09f367ae0181db9a02203c30a63b470c2394dfcafb6187105e86b736075820a304876c6d9f6092aab298ef88860879253772131b692b228043e6f625a229f0a2008382582056ca6b314617c38c921a05885f055e9b470aa793dd5d0721bee45d7192a6f0f558408e93d0a2ff6a7e7f636303454bc0415271dd9454c634e873deaf940518ff5cf984b5751509717cd2dc238e3cf88fda9e73c1d1b4d7fec1a77ff76deda1e3ed0c825820161dfc32da5b2988f1a7f31737fddda7aed8d17c0935314a671c48034973d7fe5840f92350ceb93bf61050b93b1d40069597b74db120e0758ebfd4084a2758a666c953ee5071287cec3269a08b1e600477e6935ef186797179e06580f15f0a42250c825820fc993172aa4ff02f2dec9985a6a359fb69a0ce80c85a06b3b195e54e9d0e18ae584024f7dc609084e33ca2f45ef4c3074fed634813d0123ee2daa855c536a44a1697ce3454c1d27904b939c96afe7801c7250ad7dd67eb0026ca7a3821bc05529f0e0582840000d87980821a002c43161a2f692242840100d8799f581c3ab25c853f0f188f43b34b2df5cb98737a4de7e19028ec9d1a414a550f454e45574d5fff821a004bbc491a50065788f5d90103a100a11902d1a278383361623235633835336630663138386634336233346232646635636239383733376134646537653139303238656339643161343134613535a1674e45574d5f3135b8236b616c62756d5f7469746c656f556e646572646f672c2050742e2032676172746973747381a1646e616d656b4d696b65204c65726d616e67626974726174656a323536206b6269742f7374636f6e747269627574696e675f61727469737473836b4d696b65204c65726d616e764a6f686e204b657268756c6173206f6e206472756d73781b54726f79205363687265636b206f6e206c6561642067756974617269636f7079726967687475e2849720323032332059656521205265636f72647371636f756e7472795f6f665f6f726967696e6d556e69746564205374617465736b6469737472696275746f726f68747470733a2f2f6e65776d2e696f686578706c6963697464747275656566696c657382a3696d656469615479706569617564696f2f6d7033646e616d65674d79204469636b63737263783061723a2f2f456d3958695338374939666638577832574f743247495a3635306761695254536a6b4e664a64766c754c73a3696d65646961547970656f6170706c69636174696f6e2f706466646e616d65782153747265616d696e6720526f79616c74792053686172652041677265656d656e7463737263783061723a2f2f7a4f5a3265583955435167396d5854624e57755a526b5f6859664a316e44726769677337307361305449386667656e7265738164526f636b65696d616765783061723a2f2f6559512d70635839717768334b7556636462436c51514d6b64676568756c61305a687453476f6d7a64626763697069816936373632363839393464697372636f515a2d4e57372d32332d3931343537686c616e677561676565656e2d5553656c696e6b73a16777656273697465781b687474703a2f2f6d696b656c65726d616e6d757369632e636f6d2f696c7972696369737473816b4d696b65204c65726d616e666c7972696373783061723a2f2f725268726b4f6c6f326d48445970725457415a5751676f4b4a675944706c536b5852336146546776764c6b726d6173746572696e675f656e67696e65657278184469726b20537465796572202d204143535920536f756e64696d65646961547970656a696d6167652f77656270716d657461646174615f6c616e677561676565656e2d55536c6d69785f656e67696e656572725061747269636b204275726b686f6c646572646d6f6f64781c4f6e652068756e647265642070657263656e7420747275746866756c766d757369635f6d657461646174615f76657273696f6e01646e616d65754d696b65204c65726d616e202d204d79204469636b71706172656e74616c5f61647669736f7279684578706c69636974707075626c69636174696f6e5f646174656a323032332d30342d3231727265636f7264696e675f656e67696e656572725061747269636b204275726b686f6c6465726c72656c656173655f646174656a323032332d30342d32316c72656c656173655f74797065684d756c7469706c656673657269657368556e646572646f67637365746550742e20326d736f6e675f6475726174696f6e665054334d38536a736f6e675f7469746c65674d79204469636b6e7370656369616c5f7468616e6b73816c4d6f746865722045617274686c747261636b5f6e756d626572076776657273696f6e01",
                        )
                    }
                assertThat(exception).isNotNull()
                assertThat(exception.message).isEqualTo("Unable to create the evaluation context from the given transaction.")
                assertThat(exception.jsonRpcErrorResponse).isNotNull()
                assertThat(exception.jsonRpcErrorResponse.error.code).isEqualTo(3004L)
                assertThat(exception.jsonRpcErrorResponse.error.data).isInstanceOf(CannotCreateEvaluationContextFaultData::class.java)
                assertThat(
                    (exception.jsonRpcErrorResponse.error.data as CannotCreateEvaluationContextFaultData).reason
                ).isEqualTo(
                    "Unknown transaction input (missing from UTxO set): 334958f7971903143be0672aa1818a1d96babe6cddbf445405ba488c8f70d10c#2"
                )
            }
        }
}
