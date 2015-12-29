using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class GM : MonoBehaviour {
	public static int stageLevel = 0;
	public static bool isOver = false;
	public static bool isStop = false;
	public static int score = 0 ;
	public Text t;
	public static bool mu = false;


	// Use this for initialization
	void Awake () {
	
	}
	
	// Update is called once per frame
	void Update () {
		t.text = score.ToString ();	
	}
	public void mujuck(){
		mu = true;
	}
	public void nomu(){
		mu = false;
	}
}
