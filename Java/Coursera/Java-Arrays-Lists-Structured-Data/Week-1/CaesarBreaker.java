import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CaesarBreaker
{
    int[] countLetters(String s)
    {
        int[] count=new int[26];
        s=s.toUpperCase();
        char start = 'A';
        s.chars().
                mapToObj(ch->(char)ch).
                filter(ch->ch>='A'&&ch<='Z').
                forEach(ch->count[ch-start]++);
        return count;
    }


    int indexOfMax(int[] values)
    {
        List<Integer> li = Arrays.stream(values).boxed().collect(Collectors.toList());
        return li.indexOf(li.stream().max(Integer::compareTo).orElse(li.get(0)));
    }

    String halfOfString(String message,int start)
    {
        StringBuilder ans = new StringBuilder();
        for(int i=start;i<message.length();i+=2)
            ans.append(message.charAt(i));
        return ans.toString();
    }

    String getKey(String encrypted)
    {
        CaesarCipherProcedural cipher = new CaesarCipherProcedural();
        int key = indexOfMax(countLetters(encrypted));
        if(key<4)
            key = 26-(4-key);
        else
            key=key-4;
        System.out.println("Key is :"+key);
        return cipher.encrypt(encrypted,26-key);
    }

    String joinStrings(String part1,String part2)
    {
        StringBuilder ans = new StringBuilder();
        int i=0;
        for(i=0;i<Math.min(part1.length(),part2.length());i++) {
            ans.append(part1.charAt(i));
            ans.append(part2.charAt(i));
        }
        for(;i<part1.length();i++)
            ans.append(part1.charAt(i));
        for(;i<part2.length();i++)
            ans.append(part2.charAt(i));
        return ans.toString();
    }
    String decryptTwoKeys(String encrypted)
    {
        String part1 = halfOfString(encrypted,0);
        String part2 = halfOfString(encrypted,1);

        String decryptedPart1 = getKey(part1);
        String decryptedPart2 = getKey(part2);
        return joinStrings(decryptedPart1,decryptedPart2);
    }

    void testDecryptTwoKeys()
    {
        String test="Just a test string with lots of eeeeeeeeeeeeeeeees";
        CaesarCipherProcedural cipher = new CaesarCipherProcedural();
        //System.out.println(decryptTwoKeys(cipher.encryptTwoKeys(test, 23, 2)));
        System.out.println(decryptTwoKeys("Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi gfqdse iekmfrrpzdrxzse fj xyi jzich sw tsdtlxrxzseec xifqvxic, fjkie xmmie zr xyi trwk, xyek klv nsipu rvfyeh yj zw xyvvi-hzqvrjmfrrp eeh ulijxzsew lfa xymekj zr xymj nsipu iiceki xf vetl sklvv eii melvvvrkpp xifqvximt. Xrov dsmmek e tzees xyvfyxl e hfsi-wvrqv rru gprremek e jcmxlk-gekl xyek rzfmuw gfpcmjmfrj nmkl sklvv ezvgprrvw ej kaf vbrqgpvw. Zx wyslpu klvvvjfvv esk jyitimji xyek tsdtlxzrx gvftvvkmvw esslx xyiji kvsdikvzg xymekj rru klvmi zrkiietxzse rvv tsdqfr-tceti eeh mdtfvkeex. Nlzpv klzw mj jxzpc r mecmu rvxydiex, ni afych pzov ks edieh xyek dsjx sw klv xifqvximt hyvwkmfrj giftci gfrtiir xyidwvpmij nmkl lrzv ks hf nmkl lfa xymekj rvv tservgkiu. Mk zw mdtfvkeex xyek ymxlnepw eii wljwmtmvrkpp jxiezkyx eeh wdsfxy ks wltgsix xyi himmmek sw wejx grvj, flx eesklvv ijwvrkmrp tisgiixp, aymtl av lwlecpp kebi jfv kieexvh, zw xyek ymxlnepw eii gfrkmeyfyj, mehviu tservgkmek E xf S, eeh rfx nlwk rtgvfbzqrxvpp. Xyi gfviijtfrumek wlfwmvpu fj gfqgykekmfrrp kvsdikvp zw swxvr vvjvviiu ks ej tsdtlxrxzseec ksgscsxc. R xsfh tfvkmfr sw fyi vjwsixj dep si gcejwzjziu ks fvpfrx ks xymj jysjzich eeh eii himmie sc egtcmtekmfrj zr e zrvzikc sw fxyii wmvpuw, klv gvvhzgkmfr sw klv jxiytxlvv fj jfpuiu gvfxvmew eeh xyi vvgfrjxiytxzse fj llqrr sikrrj sizrx kaf. Xyi lrpcqrvb fj slv afvb zw jrwk rpxsimkldw xyek zqgpvqvrk deklvqrxzgrp qfhvpj ks swjvv mewzkyxj zrks eeh eewniiw xf jytl ulijxzsew.\n" +
                "\n" +
                "Av rvv vbgpfvzrx zwjyvw wlgy rw lfa xvgyrzulij wsi jsczzrx gvffcidw grr fv umjgfzvvvh, zqgvfzvh, rrrppdvh, rru uidsewkvrxvh xf si gfviitx si ftkmdec. Av vbgitx xf debi pveumek gfrkvzflxzsew me tsdtlxrxzseec xifqvxic, xifqvximt dsuicmek, ueke wkvlgkyiij, lzky-giijfvdeegv tsdtlxzrx, M/S-iwjzgziegp wsi vbkiirrp qvqfvp, kvsxvrtymt zrwsiqrxzse jcjxvqj (KZW), fzscsxmtec tsdtlxzrx, eeh hrxr tsdtiijwzse.\n" +
                "\n" +
                "Sitelwv fj xyi kvsdikvzg rrxlvv fj xyi tycjmtec nsipu zr aymtl av cmmi, xifqvximt gvffcidw eimji me rrp rvve xyek zrkiietxj nmkl xyi tycjmtec nsipu. Kvsdikvzg gfqgykmek jfglwvw se uijmxrzrx, eeeccqmek, rru zqgpvqvrkmek iwjzgziex eckfvzxyqj wsi xifqvximt gvffcidw. Klv xifqvximt tsdtlxzrx xvfyg fj xyi hvtrvkqvrk zw mexvveekmfrrpcc vvrfaeiu wsi zxj tseximsykmfrj ks xyi jlruediexrp tisspvqj zr gfqgykekmfrrp kvsdikvp, euhiijwzrx dejwzzv ueke qrrrkvqvrk zwjyvw me xifqvximt gvffcidw, rru rtgppmek kvsdikvzg xvgyrzulij ks e zrvzikc sw rvvej, megcyumek qfpvglprv fzscsxc, xifqvximt dsuicmek, issskmtw, xifkieglzg mejfvdekmfr wpwkidw, vgfpfkp, eeh tysksemtw.\n" +
                "\n" +
                "Xyi kislt etxzzvpp tscprffvrxvw azxy fxyii xvfygw azxyme klv uigeixdiex eeh azxy klv iijirvtlvvj zr sklvv hvtrvkqvrkw ek Uybi. Klvc gfpcessieki azxy wetycxp zr Qrxyidekmtw, Smfpfkp, Fzstlvqzwkvp, Icitximtec rru Tsdtlxvv Iekzrviimek, rru klv Emtlfprw Wtlfsc fj Iezzvfrdiex. Sipseh Hlov, xyi kislt ecwf tscprffvrxvw azxy iijirvtlvvj rx zrvzslw xft mewkmkykij. Fvgryji sw zxj uigxy rru svveuxy, xyi kvsdikvzg gfqgykmek kislt ek Uybi mj rvxyrfcc xyi xft kvsdikvzg gfqgykmek kislt me klv eekmfr."));
        System.out.println(cipher.encryptTwoKeys("Top ncmy qkff vi vguv vbg ycpx",26-2,26-20));
    }

    public static void main(String[] args) {
        CaesarBreaker breaker = new CaesarBreaker();
        breaker.testDecryptTwoKeys();
    }
}
