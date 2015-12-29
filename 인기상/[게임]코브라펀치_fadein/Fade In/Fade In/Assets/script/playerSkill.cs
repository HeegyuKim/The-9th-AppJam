using UnityEngine;
using System.Collections;
using UnityEngine.UI;
public class playerSkill : MonoBehaviour {
	public GameObject skill;
	float coolTime =  0f;
	bool isCool = false;
	public Image coolTrue;
	public Image coolFalse;
	public Text coolText;
	// Use this for initialization
	void Awake () {
		coolFalse.color = new Color(coolFalse.color.r,coolFalse.color.g,coolFalse.color.b,1);
		coolTrue.color = new Color(coolTrue.color.r,coolTrue.color.g,coolTrue.color.b,0);
		coolText.color = coolTrue.color;
	}
	
	// Update is called once per frame
	void Update () {
		if (GM.isOver)
			return;
		coolText.text = ((int)(5 - coolTime)).ToString();
		if (isCool) {
			coolTime += Time.deltaTime;
			if (coolTime >= 5f) {
				isCool = false;
				coolTime = 0f;
				coolFalse.color = new Color(coolFalse.color.r,coolFalse.color.g,coolFalse.color.b,1);
				coolTrue.color = new Color(coolTrue.color.r,coolTrue.color.g,coolTrue.color.b,0);
				coolText.color = coolTrue.color;
			}
		} else {
		}

		if(Input.GetMouseButtonUp(0)) {
			if(!isCool){
			GameObject gs = Instantiate(skill);
				isCool = true;
				coolTime = 0f;
				coolFalse.color = new Color(coolFalse.color.r,coolFalse.color.g,coolFalse.color.b,0);
				coolTrue.color = new Color(coolTrue.color.r,coolTrue.color.g,coolTrue.color.b,1);
				coolText.color = coolTrue.color;
			}


		}
	}
}
